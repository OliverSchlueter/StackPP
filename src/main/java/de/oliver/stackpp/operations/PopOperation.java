package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.function.Function;

public class PopOperation extends Operation{

    private final Function<Program, Register<Integer>> register;

    public PopOperation(Program program, Function<Program, Register<Integer>> register) {
        super(program);
        this.register = register;
    }

    @Override
    public void execute() {
        register.apply(program).setValue(program.getStack().pop());
    }

    public Register<Integer> getRegister(){
        return register.apply(program);
    }
}
