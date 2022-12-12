package de.oliver.stackpp.operations;

import de.oliver.stackpp.Program;

public class PushOperation extends Operation{

    private final int value;

    public PushOperation(Program program, int value) {
        super(program);
        this.value = value;
    }

    @Override
    public void execute() {
        program.getStack().push(value);
    }

    public int getValue() {
        return value;
    }
}
