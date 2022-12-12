package de.oliver.stackpp;

import de.oliver.stackpp.operations.*;
import de.oliver.stackpp.virtualMachine.Program;

import java.util.LinkedList;
import java.util.NoSuchElementException;

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
            case PUSH_LITERAL -> operation = new PushOperation(program, Integer.parseInt(instruction.args()[0]));
            case PUSH_REGISTER -> operation = new PushOperation(program, instruction.args()[0]);
            case POP -> operation = new PopOperation(program, instruction.args()[0]);
            case MOVE -> operation = new MoveOperation(program, Integer.parseInt(instruction.args()[0]), instruction.args()[1]);
            case ADD -> operation = new AddOperation(program, instruction.args()[0], instruction.args()[1]);
            case SUBTRACT -> operation = new SubtractOperation(program, instruction.args()[0], instruction.args()[1]);
            case MULTIPLY -> operation = new MultiplyOperation(program, instruction.args()[0], instruction.args()[1]);
            case DIVIDE -> operation = new DivideOperation(program, instruction.args()[0], instruction.args()[1]);
            case MODULO -> operation = new ModuloOperation(program, instruction.args()[0], instruction.args()[1]);
            case PRINT -> operation = new PrintOperation(program, instruction.args()[0]);
            case PRINT_STACK -> operation = new PrintStackOperation(program);

            default -> throw new NoSuchElementException("Could not find operation token");
        }

        program.getInstructions().offer(operation);
    }

    public LinkedList<Instruction> getInstructions() {
        return instructions;
    }

    public Program getProgram() {
        return program;
    }
}
