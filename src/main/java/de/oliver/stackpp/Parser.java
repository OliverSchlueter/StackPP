package de.oliver.stackpp;

import de.oliver.stackpp.operations.BlockOperation;
import de.oliver.stackpp.operations.CompileOperation;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.operations.impl.StringOperation;
import de.oliver.stackpp.operations.impl.SyscallOperation;
import de.oliver.stackpp.operations.impl.ThrowOperation;
import de.oliver.stackpp.operations.impl.arithmetic.*;
import de.oliver.stackpp.operations.impl.bitwise.*;
import de.oliver.stackpp.operations.impl.block.*;
import de.oliver.stackpp.operations.impl.memory.*;
import de.oliver.stackpp.operations.impl.register.*;
import de.oliver.stackpp.operations.impl.stack.PopOperation;
import de.oliver.stackpp.operations.impl.stack.PrintStackOperation;
import de.oliver.stackpp.operations.impl.stack.PushOperation;
import de.oliver.stackpp.virtualMachine.Register;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.function.Function;

public class Parser {

    private final LinkedList<Instruction> instructions;
    private Program program;
    private final Stack<BlockOperation> waitForEnd;

    public Parser(LinkedList<Instruction> instructions) {
        this.instructions = instructions;
        this.waitForEnd = new Stack<>();
    }

    public Program parse(){
        this.program = new Program();

        for (Instruction instruction : instructions) {
            Operation operation = parseInstruction(instruction);

            if(operation instanceof CompileOperation compileOperation){
                compileOperation.executeOnCompile(program);
            }

            if(waitForEnd.size() > 0){
                // put operation into the block
                waitForEnd.lastElement().addOperation(operation);
            } else {
                // put operation into program-block
                this.program.getInstructions().offer(operation);
            }

            if(operation instanceof BlockOperation blockOperation){
                // open new block
                waitForEnd.push(blockOperation);

            }else if(operation instanceof ElseOperation){
                // switch into the else-block
                ((IfOperation) waitForEnd.lastElement()).setInElse(true);
                continue;

            } else if(operation instanceof EndOperation){
                // close most recent block
                waitForEnd.pop();
                continue;
            }

        }

        return this.program;
    }

    private Operation parseInstruction(Instruction instruction){
        Operation operation = null;

        switch (instruction.token()){
            case PUSH -> {
                Function<Program, Integer> value = getIntegerValueFromString(instruction.args()[0]);
                operation = new PushOperation(program, instruction.line(), value);
            }

            case POP -> {
                Function<Program, Register<Integer>> register = getIntegerRegisterFromString(instruction.args()[0]);
                operation = new PopOperation(program, instruction.line(), register);
            }

            case MOVE -> {
                Function<Program, Integer> a = getIntegerValueFromString(instruction.args()[0]);
                Function<Program, Register<Integer>> b = getIntegerRegisterFromString(instruction.args()[1]);

                operation = new MoveOperation(program, instruction.line(), a, b);
            }

            case MOVE_FLOAT -> {
                Function<Program, Float> a = getFloatValueFromString(instruction.args()[0]);
                Function<Program, Register<Float>> b = getFloatRegisterFromString(instruction.args()[1]);

                operation = new MoveFloatOperation(program, instruction.line(), a, b);
            }

            case ADD, SUBTRACT, MULTIPLY, MODULO, LEFT_SHIFT, RIGHT_SHIFT, BITWISE_AND, BITWISE_OR, BITWISE_XOR -> {
                String aStr = instruction.args()[0];
                String bStr = instruction.args()[1];

                Function<Program, Register<Integer>> a = getIntegerRegisterFromString(aStr);
                Function<Program, Integer> b = getIntegerValueFromString(bStr);

                switch (instruction.token()){
                    case ADD -> operation = new AddOperation(program, instruction.line(), a, b);
                    case SUBTRACT -> operation = new SubtractOperation(program, instruction.line(), a, b);
                    case MULTIPLY -> operation = new MultiplyOperation(program, instruction.line(), a, b);
                    case MODULO -> operation = new ModuloOperation(program, instruction.line(), a, b);
                    case LEFT_SHIFT -> operation = new LeftShiftOperation(program, instruction.line(), a, b);
                    case RIGHT_SHIFT -> operation = new RightShiftOperation(program, instruction.line(), a, b);
                    case BITWISE_AND -> operation = new AndOperation(program, instruction.line(), a, b);
                    case BITWISE_OR -> operation = new OrOperation(program, instruction.line(), a, b);
                    case BITWISE_XOR -> operation = new XorOperation(program, instruction.line(), a, b);
                }
            }

            case ADD_FLOAT -> {
                Function<Program, Register<Float>> a = getFloatRegisterFromString(instruction.args()[0]);
                Function<Program, Float> b = getFloatValueFromString(instruction.args()[1]);
                operation = new AddFloatOperation(program, instruction.line(), a, b);
            }

            case DIVIDE -> {
                Function<Program, Register<Float>> a = getFloatRegisterFromString(instruction.args()[0]);
                Function<Program, Float> b = getFloatValueFromString(instruction.args()[1]);
                operation = new DivideOperation(program, instruction.line(), a, b);
            }

            case BITWISE_NOT -> {
                Function<Program, Register<Integer>> a = getIntegerRegisterFromString(instruction.args()[0]);
                operation = new NotOperation(program, instruction.line(), a);
            }

            case PRINT -> {
                Function<Program, Register<Integer>> register = getIntegerRegisterFromString(instruction.args()[0]);
                operation = new PrintOperation(program, instruction.line(), register);
            }

            case PRINT_FLOAT -> {
                Function<Program, Register<Float>> register = getFloatRegisterFromString(instruction.args()[0]);
                operation = new PrintFloatOperation(program, instruction.line(), register);
            }

            case ASCII_PRINT -> {
                Function<Program, Integer> charr = getIntegerValueFromString(instruction.args()[0]);
                operation = new AsciiPrintOperation(program, instruction.line(), charr);
            }

            case PRINT_STACK -> operation = new PrintStackOperation(program, instruction.line());

            case IF -> {
                Function<Program, Integer> a = getIntegerValueFromString(instruction.args()[0]);
                Token compareOperation = Token.getTokenByIdentifier(instruction.args()[1]);
                Function<Program, Integer> b = getIntegerValueFromString(instruction.args()[2]);

                operation = new IfOperation(program, instruction.line(), a, b, compareOperation);
            }

            case ELSE -> operation = new ElseOperation(program, instruction.line());

            case WHILE -> {
                Function<Program, Integer> a = getIntegerValueFromString(instruction.args()[0]);
                Token compareOperation = Token.getTokenByIdentifier(instruction.args()[1]);
                Function<Program, Integer> b = getIntegerValueFromString(instruction.args()[2]);

                operation = new WhileOperation(program, instruction.line(), a, b, compareOperation);
            }

            case FUNCTION -> {
                String name = instruction.args()[0];

                operation = new FunctionOperation(program, instruction.line(), name);
            }

            case CALL -> {
                String name = instruction.args()[0];

                operation = new CallOperation(program, instruction.line(), name);
            }

            case END -> operation = new EndOperation(program, instruction.line());

            case MEM_SET -> {
                Function<Program, Integer> index = getIntegerValueFromString(instruction.args()[0]);
                Function<Program, Byte> value = getByteValueFromString(instruction.args()[1]);

                operation = new MemorySetOperation(program, instruction.line(), index, value);
            }

            case MEM_GET -> {
                Function<Program, Integer> index = getIntegerValueFromString(instruction.args()[0]);

                operation = new MemoryGetOperation(program, instruction.line(), index);
            }

            case MEM_ALLOC -> {
                Function<Program, Integer> size = getIntegerValueFromString(instruction.args()[0]);
                operation = new MemoryAllocOperation(program, instruction.line(), size);
            }

            case MEM_FREE -> {
                Function<Program, Integer> ptr = getIntegerValueFromString(instruction.args()[0]);
                operation = new MemoryFreeOperation(program, instruction.line(), ptr);
            }

            case MEM_DUMP -> operation = new MemoryDumpOperation(program, instruction.line());

            case SYSCALL -> {
                Function<Program, Integer> id = getIntegerValueFromString(instruction.args()[0]);
                operation = new SyscallOperation(program, instruction.line(), id);
            }

            case STRING -> operation = new StringOperation(program, instruction.line(), instruction.args());

            case THROW -> {
                Function<Program, Integer> msgPtr = getIntegerValueFromString(instruction.args()[0]);
                operation = new ThrowOperation(program, instruction.line(), msgPtr);
            }

            default -> throw new NoSuchElementException("Could not find operation token");
        }

        return operation;
    }

    private Function<Program, Integer> getIntegerFromString(String s){
        return p -> Integer.parseInt(s);
    }

    private Function<Program, Byte> getByteFromString(String s){
        return p -> Byte.parseByte(s);
    }

    private Function<Program, Float> getFloatFromString(String s){
        return p -> Float.parseFloat(s);
    }

    private Function<Program, Register<Integer>> getIntegerRegisterFromString(String s){
        return p -> program.getMachine().getIntegerRegister(s);
    }

    private Function<Program, Byte> getByteValueFromString(String s){
        if(NumberUtils.isNumber(s)){
            return getByteFromString(s);
        } else {
            return p -> p.getMachine().getIntegerRegister(s).getValue().byteValue();
        }
    }

    private Function<Program, Register<Float>> getFloatRegisterFromString(String s){
        return p -> program.getMachine().getFloatRegister(s);
    }

    private Function<Program, Integer> getIntegerValueFromString(String s){
        Function<Program, Integer> func = null;

        // check if it is a register
        if (NumberUtils.isNumber(s)) {
            func = getIntegerFromString(s);
        } else {
            func = p -> program.getMachine().getIntegerRegister(s).getValue();
        }

        return func;
    }

    private Function<Program, Float> getFloatValueFromString(String s){
        Function<Program, Float> func = null;

        // check if it is a register
        if (NumberUtils.isNumber(s)) {
            func = getFloatFromString(s);
        } else {
            func = p -> program.getMachine().getFloatRegister(s).getValue();
        }

        return func;
    }

    public LinkedList<Instruction> getInstructions() {
        return instructions;
    }

    public Program getProgram() {
        return program;
    }
}
