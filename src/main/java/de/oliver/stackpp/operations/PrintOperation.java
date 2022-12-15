package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.function.Function;

public class PrintOperation extends Operation{

    private final Function<Program, Register<Integer>> register;

    public PrintOperation(Program program, Function<Program, Register<Integer>> register) {
        super(program);
        this.register = register;
    }

    @Override
    public void execute() {
        System.out.println(register.apply(program).getValue());
    }
}
