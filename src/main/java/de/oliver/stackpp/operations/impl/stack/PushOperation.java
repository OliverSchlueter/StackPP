package de.oliver.stackpp.operations.impl.stack;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.Program;

import java.util.function.Function;

public class PushOperation extends Operation {

    private final Function<Program, Integer> value;

    public PushOperation(Program program, int line, Function<Program, Integer> value) {
        super(program, line);
        this.value = value;
    }

    @Override
    public void execute() {
        program.getMachine().getStack().push(value.apply(program));
    }

    public Function<Program, Integer> getValue() {
        return value;
    }
}
