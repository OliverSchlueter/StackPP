package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.function.Function;

public class SubtractOperation extends Operation{


    private final Function<Program, Register<Integer>> a;
    private final Function<Program, Integer> b;

    public SubtractOperation(Program program, Function<Program, Register<Integer>> a, Function<Program, Integer> b) {
        super(program);
        this.a = a;
        this.b = b;
    }

    @Override
    public void execute() {
        Register<Integer> aReg = a.apply(program);
        int bVal = b.apply(program);

        int result = aReg.getValue() - bVal;

        aReg.setValue(result);
    }
}
