package de.oliver.stackpp;

import de.oliver.stackpp.operations.*;
import de.oliver.stackpp.virtualMachine.Program;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class Parser {

    private LinkedList<Instruction> instructions;
    private Program program;

    public Parser(LinkedList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Program parse(){
        program = new Program();

        for (Instruction instruction : instructions) {
            parseInstruction(instruction);
        }

        return program;
    }

    private void parseInstruction(Instruction instruction){
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

            default -> throw new NoSuchElementException("Could not find operation token");
        }

        program.getInstructions().offer(operation);
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
