package de.oliver.stackpp.operations;


import de.oliver.stackpp.Program;

public abstract class Operation {

    protected final Program program;
    protected final int line;

    public Operation(Program program, int line) {
        this.program = program;
        this.line = line;
    }

    public abstract void execute();

    public int getLine() {
        return line;
    }
}
