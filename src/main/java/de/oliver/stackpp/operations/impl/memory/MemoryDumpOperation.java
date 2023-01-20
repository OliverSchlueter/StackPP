package de.oliver.stackpp.operations.impl.memory;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.Program;

public class MemoryDumpOperation extends Operation {

    public MemoryDumpOperation(Program program, int line) {
        super(program, line);
    }

    @Override
    public void execute() {
        program.getMachine().getMemory().dump();
    }
}
