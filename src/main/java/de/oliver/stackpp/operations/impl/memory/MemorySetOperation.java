package de.oliver.stackpp.operations.impl.memory;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.Program;

import java.util.function.Function;

public class MemorySetOperation extends Operation {

    private final Function<Program, Integer> index;
    private final Function<Program, Byte> value;

    public MemorySetOperation(Program program, int line, Function<Program, Integer> index, Function<Program, Byte> value) {
        super(program, line);
        this.index = index;
        this.value = value;
    }

    @Override
    public void execute() {
        program.getMachine().getMemory().setAt(index.apply(program), value.apply(program));
    }

    public Function<Program, Integer> getIndex() {
        return index;
    }

    public Function<Program, Byte> getValue() {
        return value;
    }
}
