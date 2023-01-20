package de.oliver.stackpp.operations.impl.register;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.function.Function;

public class PrintFloatOperation extends Operation {

    private final Function<Program, Register<Float>> register;

    public PrintFloatOperation(Program program, int line, Function<Program, Register<Float>> register) {
        super(program, line);
        this.register = register;
    }

    @Override
    public void execute() {
        System.out.println(register.apply(program).getValue());
    }

    public Function<Program, Register<Float>> getRegister() {
        return register;
    }
}
