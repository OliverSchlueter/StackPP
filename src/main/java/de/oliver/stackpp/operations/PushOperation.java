package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;

import java.util.function.Function;

public class PushOperation extends Operation{

    private final Function<Program, Integer> value;

    public PushOperation(Program program, Function<Program, Integer> value) {
        super(program);
        this.value = value;
    }

    @Override
    public void execute() {
        program.getStack().push(value.apply(program));
    }

    public int getValue(){
        return value.apply(program);
    }
}
