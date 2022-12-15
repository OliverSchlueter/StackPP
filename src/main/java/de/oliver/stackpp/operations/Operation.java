package de.oliver.stackpp.operations;


import de.oliver.stackpp.virtualMachine.Program;

public abstract class Operation {

    protected final Program programm;

    public Operation(Program program) {
        this.programm = program;
    }

    public abstract void execute(Program program);

}
