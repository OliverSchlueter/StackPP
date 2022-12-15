package de.oliver.stackpp;

import de.oliver.stackpp.operations.*;
import de.oliver.stackpp.virtualMachine.Program;
import de.oliver.stackpp.virtualMachine.Register;

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
                Function<Program, Integer> value = getValueFromString(instruction.args()[0]);
                operation = new PushOperation(program, value);
            }

            case POP -> {
                Function<Program, Register<Integer>> register = getRegisterFromString(instruction.args()[0]);
                operation = new PopOperation(program, register);
            }

            case MOVE -> {
                Function<Program, Integer> a = getValueFromString(instruction.args()[0]);
                Function<Program, Register<Integer>> b = getRegisterFromString(instruction.args()[1]);

                operation = new MoveOperation(program, a, b);
            }

            case ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULO -> {
                String aStr = instruction.args()[0];
                String bStr = instruction.args()[1];

                Function<Program, Register<Integer>> a = getRegisterFromString(aStr);
                Function<Program, Integer> b = getValueFromString(bStr);

                switch (instruction.token()){
                    case ADD -> operation = new AddOperation(program, a, b);
                    case SUBTRACT -> operation = new SubtractOperation(program, a, b);
                    case MULTIPLY -> operation = new MultiplyOperation(program, a, b);
                    case DIVIDE -> operation = new DivideOperation(program, a, b);
                    case MODULO -> operation = new ModuloOperation(program, a, b);
                }
            }

            case PRINT -> {
                Function<Program, Register<Integer>> register = getRegisterFromString(instruction.args()[0]);
                operation = new PrintOperation(program, register);
            }

            case PRINT_STACK -> operation = new PrintStackOperation(program);

            case IF -> {
                Function<Program, Integer> a = getValueFromString(instruction.args()[0]);
                Token compareOperation = Token.getTokenByIdentifier(instruction.args()[1]);
                Function<Program, Integer> b = getValueFromString(instruction.args()[2]);

                operation = new IfOperation(program, a, b, compareOperation);
            }

            case ELSE -> operation = new ElseOperation(program);

            case WHILE -> {
                Function<Program, Integer> a = getValueFromString(instruction.args()[0]);
                Token compareOperation = Token.getTokenByIdentifier(instruction.args()[1]);
                Function<Program, Integer> b = getValueFromString(instruction.args()[2]);

                operation = new WhileOperation(program, a, b, compareOperation);
            }

            case FUNCTION -> {
                String name = instruction.args()[0];

                operation = new FunctionOperation(program, name);
            }

            case CALL -> {
                String name = instruction.args()[0];

                operation = new CallOperation(program, name);
            }

            case END -> operation = new EndOperation(program);


            default -> throw new NoSuchElementException("Could not find operation token");
        }

        return operation;
    }

    private Function<Program, Integer> getIntegerFromString(String s){
        return p -> Integer.parseInt(s);
    }

    private Function<Program, Register<Integer>> getRegisterFromString(String s){
        if(!program.getRegisters().containsKey(s)){
            throw new NullPointerException("Could not find a register with the name '" + s + "'");
        }

        return p -> p.getRegisters().get(s);
    }

    private Function<Program, Integer> getValueFromString(String s){
        Function<Program, Integer> func = null;

        // check if it is a register
        if(program.getRegisters().containsKey(s)){
            func = p -> p.getRegisters().get(s).getValue();
        } else {
            // try parse to literal
            func = getIntegerFromString(s);
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
