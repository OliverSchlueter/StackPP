package de.oliver.stackpp.operations.impl;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;

public class NoOperationOperation extends Operation {

    public NoOperationOperation(Program program, int line) {
        super(program, line);
    }

    @Override
    public void execute() {

    }
}
