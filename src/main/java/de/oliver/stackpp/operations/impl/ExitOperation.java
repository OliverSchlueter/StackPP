package de.oliver.stackpp.operations.impl;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Program;

import java.util.function.Function;

public class ExitOperation extends Operation {

    private final Function<Program, Integer> exitCode;

    public ExitOperation(Program program, Function<Program, Integer> exitCode) {
        super(program);
        this.exitCode = exitCode;
    }

    @Override
    public void execute() {
        program.exit(exitCode.apply(program));
    }

    public Function<Program, Integer> getExitCode() {
        return exitCode;
    }
}
