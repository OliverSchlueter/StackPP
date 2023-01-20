package de.oliver.stackpp.operations.impl.register;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.function.Function;

public class MoveFloatOperation extends Operation {

    private final Function<Program, Float> value;
    private final Function<Program, Register<Float>> register;


    public MoveFloatOperation(Program program, int line, Function<Program, Float> value, Function<Program, Register<Float>> register) {
        super(program, line);
        this.value = value;
        this.register = register;
    }

    @Override
    public void execute() {
        Register<Float> reg = register.apply(program);
        float val = value.apply(program);

        reg.setValue(val);
    }

    public Function<Program, Float> getValue() {
        return value;
    }

    public Function<Program, Register<Float>> getRegister() {
        return register;
    }
}
