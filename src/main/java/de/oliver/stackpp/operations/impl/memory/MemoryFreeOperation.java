package de.oliver.stackpp.operations.impl.memory;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.Program;

import java.util.function.Function;

public class MemoryFreeOperation extends Operation {

    private final Function<Program, Integer> ptr;

    public MemoryFreeOperation(Program program, int line, Function<Program, Integer> ptr) {
        super(program, line);
        this.ptr = ptr;
    }

    @Override
    public void execute() {
        program.getMachine().getMemory().free(ptr.apply(program));
    }

    public Function<Program, Integer> getPtr() {
        return ptr;
    }
}
