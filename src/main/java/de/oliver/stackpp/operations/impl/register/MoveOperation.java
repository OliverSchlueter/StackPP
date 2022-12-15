package de.oliver.stackpp.operations.impl.register;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Program;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.function.Function;

public class MoveOperation extends Operation {

    private final Function<Program, Integer> value;
    private final Function<Program, Register<Integer>> register;

    public MoveOperation(Program program, Function<Program, Integer> value, Function<Program, Register<Integer>> register) {
        super(program);
        this.value = value;
        this.register = register;
    }

    @Override
    public void execute() {
        Register<Integer> reg = register.apply(program);
        int val = value.apply(program);

        reg.setValue(val);
    }

    public Function<Program, Integer> getValue() {
        return value;
    }

    public Function<Program, Register<Integer>> getRegister() {
        return register;
    }
}
