package de.oliver.stackpp.operations.impl.memory;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Program;

public class MemoryDumpOperation extends Operation {

    public MemoryDumpOperation(Program program) {
        super(program);
    }

    @Override
    public void execute() {
        program.getMachine().getMemory().dump();
    }
}
