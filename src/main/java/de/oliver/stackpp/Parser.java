package de.oliver.stackpp;

import de.oliver.stackpp.operations.BlockOperation;
import de.oliver.stackpp.operations.CompileOperation;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.operations.impl.NoOperationOperation;
import de.oliver.stackpp.operations.impl.StringOperation;
import de.oliver.stackpp.operations.impl.SyscallOperation;
import de.oliver.stackpp.operations.impl.ThrowOperation;
import de.oliver.stackpp.operations.impl.arithmetic.*;
import de.oliver.stackpp.operations.impl.bitwise.*;
import de.oliver.stackpp.operations.impl.block.*;
import de.oliver.stackpp.operations.impl.memory.*;
import de.oliver.stackpp.operations.impl.register.AsciiPrintOperation;
import de.oliver.stackpp.operations.impl.register.MoveOperation;
import de.oliver.stackpp.operations.impl.register.PrintOperation;
import de.oliver.stackpp.operations.impl.stack.PopOperation;
import de.oliver.stackpp.operations.impl.stack.PrintStackOperation;
import de.oliver.stackpp.operations.impl.stack.PushOperation;
import de.oliver.stackpp.utils.ExceptionHelper;
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
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Integer> value = getValueFromString(instruction.args()[0]);
                operation = new PushOperation(program, instruction.line(), value);
            }

            case POP -> {
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Register<Integer>> register = getRegisterFromString(instruction.args()[0]);
                operation = new PopOperation(program, instruction.line(), register);
            }

            case MOVE -> {
                if(instruction.args().length < 2){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Integer> a = getValueFromString(instruction.args()[0]);
                Function<Program, Register<Integer>> b = getRegisterFromString(instruction.args()[1]);

                operation = new MoveOperation(program, instruction.line(), a, b);
            }

            case ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULO, LEFT_SHIFT, RIGHT_SHIFT, BITWISE_AND, BITWISE_OR, BITWISE_XOR -> {
                if(instruction.args().length < 2){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                String aStr = instruction.args()[0];
                String bStr = instruction.args()[1];

                Function<Program, Register<Integer>> a = getRegisterFromString(aStr);
                Function<Program, Integer> b = getValueFromString(bStr);

                switch (instruction.token()){
                    case ADD -> operation = new AddOperation(program, instruction.line(), a, b);
                    case SUBTRACT -> operation = new SubtractOperation(program, instruction.line(), a, b);
                    case MULTIPLY -> operation = new MultiplyOperation(program, instruction.line(), a, b);
                    case DIVIDE -> operation = new DivideOperation(program, instruction.line(), a, b);
                    case MODULO -> operation = new ModuloOperation(program, instruction.line(), a, b);
                    case LEFT_SHIFT -> operation = new LeftShiftOperation(program, instruction.line(), a, b);
                    case RIGHT_SHIFT -> operation = new RightShiftOperation(program, instruction.line(), a, b);
                    case BITWISE_AND -> operation = new AndOperation(program, instruction.line(), a, b);
                    case BITWISE_OR -> operation = new OrOperation(program, instruction.line(), a, b);
                    case BITWISE_XOR -> operation = new XorOperation(program, instruction.line(), a, b);
                }
            }

            case INCREMENT -> {
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Register<Integer>> a = getRegisterFromString(instruction.args()[0]);
                operation = new IncrementOperation(program, instruction.line(), a);
            }

            case DECREMENT -> {
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Register<Integer>> a = getRegisterFromString(instruction.args()[0]);
                operation = new DecrementOperation(program, instruction.line(), a);
            }

            case BITWISE_NOT -> {
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Register<Integer>> a = getRegisterFromString(instruction.args()[0]);
                operation = new NotOperation(program, instruction.line(), a);
            }

            case PRINT -> {
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Register<Integer>> register = getRegisterFromString(instruction.args()[0]);
                operation = new PrintOperation(program, instruction.line(), register);
            }

            case ASCII_PRINT -> {
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Integer> charr = getValueFromString(instruction.args()[0]);
                operation = new AsciiPrintOperation(program, instruction.line(), charr);
            }

            case PRINT_STACK -> operation = new PrintStackOperation(program, instruction.line());

            case IF -> {
                if(instruction.args().length < 3){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Integer> a = getValueFromString(instruction.args()[0]);
                Token compareOperation = Token.getTokenByIdentifier(instruction.args()[1]);
                Function<Program, Integer> b = getValueFromString(instruction.args()[2]);

                operation = new IfOperation(program, instruction.line(), a, b, compareOperation);
            }

            case ELSE -> operation = new ElseOperation(program, instruction.line());

            case WHILE -> {
                if(instruction.args().length < 3){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Integer> a = getValueFromString(instruction.args()[0]);
                Token compareOperation = Token.getTokenByIdentifier(instruction.args()[1]);
                Function<Program, Integer> b = getValueFromString(instruction.args()[2]);

                operation = new WhileOperation(program, instruction.line(), a, b, compareOperation);
            }

            case FUNCTION -> {
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                String name = instruction.args()[0];
                operation = new FunctionOperation(program, instruction.line(), name);
            }

            case CALL -> {
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                String name = instruction.args()[0];
                operation = new CallOperation(program, instruction.line(), name);
            }

            case END -> operation = new EndOperation(program, instruction.line());

            case BREAK -> {
                Function<Program, WhileOperation> whileBlock = null;
                for (BlockOperation blockOperation : waitForEnd) {
                    if(blockOperation instanceof WhileOperation wOp){
                        whileBlock = p -> wOp;
                    }
                }

                operation = new BreakOperation(program, instruction.line(), whileBlock);
            }

            case CONTINUE -> {
                Function<Program, WhileOperation> whileBlock = null;
                for (BlockOperation blockOperation : waitForEnd) {
                    if(blockOperation instanceof WhileOperation wOp){
                        whileBlock = p -> wOp;
                    }
                }

                operation = new ContinueOperation(program, instruction.line(), whileBlock);
            }

            case MEM_SET -> {
                if(instruction.args().length < 2){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Integer> index = getValueFromString(instruction.args()[0]);
                Function<Program, Byte> value = getByteFromString(instruction.args()[1]);

                operation = new MemorySetOperation(program, instruction.line(), index, value);
            }

            case MEM_GET -> {
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Integer> index = getValueFromString(instruction.args()[0]);
                operation = new MemoryGetOperation(program, instruction.line(), index);
            }

            case MEM_ALLOC -> {
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Integer> size = getValueFromString(instruction.args()[0]);
                operation = new MemoryAllocOperation(program, instruction.line(), size);
            }

            case MEM_FREE -> {
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Integer> ptr = getValueFromString(instruction.args()[0]);
                operation = new MemoryFreeOperation(program, instruction.line(), ptr);
            }

            case MEM_DUMP -> operation = new MemoryDumpOperation(program, instruction.line());

            case SYSCALL -> {
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Integer> id = getValueFromString(instruction.args()[0]);
                operation = new SyscallOperation(program, instruction.line(), id);
            }

            case STRING -> operation = new StringOperation(program, instruction.line(), instruction.args());

            case THROW -> {
                if(instruction.args().length < 1){
                    ExceptionHelper.throwException(instruction.line(), "Missing arguments");
                    System.exit(1);
                }

                Function<Program, Integer> msgPtr = getValueFromString(instruction.args()[0]);
                operation = new ThrowOperation(program, instruction.line(), msgPtr);
            }

            case NO_OPERATION -> operation = new NoOperationOperation(program, instruction.line());

            default -> throw new NoSuchElementException("Could not find operation token");
        }

        return operation;
    }

    private Function<Program, Integer> getIntegerFromString(String s){
        return p -> Integer.parseInt(s);
    }

    private Function<Program, Byte> getByteFromString(String s){
        if(NumberUtils.isNumber(s)){
            return p -> Byte.parseByte(s);
        } else {
            return p -> p.getMachine().getRegister(s).getValue().byteValue();
        }
    }

    private Function<Program, Register<Integer>> getRegisterFromString(String s){
        return p -> program.getMachine().getRegister(s);
    }

    private Function<Program, Integer> getValueFromString(String s){
        Function<Program, Integer> func = null;

        // check if it is a register
        if (NumberUtils.isNumber(s)) {
            func = getIntegerFromString(s);
        } else {
            func = p -> program.getMachine().getRegister(s).getValue();
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
