package de.oliver.stackpp.operations.impl.stack;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Program;

import java.util.function.Function;

public class PushOperation extends Operation {

    private final Function<Program, Integer> value;

    public PushOperation(Program program, Function<Program, Integer> value) {
        super(program);
        this.value = value;
    }

    @Override
    public void execute() {
        program.getMachine().getStack().push(value.apply(program));
    }

}
