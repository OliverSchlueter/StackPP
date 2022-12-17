package de.oliver.stackpp.operations.impl.memory;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.Program;

import java.util.function.Function;

public class MemoryGetOperation extends Operation {

    private final Function<Program, Integer> index;

    public MemoryGetOperation(Program program, Function<Program, Integer> index) {
        super(program);
        this.index = index;
    }

    @Override
    public void execute() {
        byte value = program.getMachine().getMemory().getAt(index.apply(program));

        program.getMachine().getStack().push((int) value);
    }

    public Function<Program, Integer> getIndex() {
        return index;
    }
}
