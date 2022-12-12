package de.oliver.stackpp.operations;


import de.oliver.stackpp.Program;

public abstract class Operation {

    protected final Program program;

    public Operation(Program program) {
        this.program = program;
    }

    public abstract void execute();

}
