package de.oliver.stackpp.operations.impl.memory;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.Program;

import java.util.function.Function;

public class MemoryAllocOperation extends Operation {

    private final Function<Program, Integer> size;

    public MemoryAllocOperation(Program program, Function<Program, Integer> size) {
        super(program);
        this.size = size;
    }

    @Override
    public void execute() {
        int ptr = program.getMachine().getMemory().allocate(size.apply(program));
        program.getMachine().getStack().push(ptr);
    }

    public Function<Program, Integer> getSize() {
        return size;
    }
}
