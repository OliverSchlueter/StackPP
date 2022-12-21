package de.oliver.stackpp.operations.impl.bitwise;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.function.Function;

public class AndOperation extends Operation {

    private final Function<Program, Register<Integer>> a;
    private final Function<Program, Integer> b;

    public AndOperation(Program program, Function<Program, Register<Integer>> a, Function<Program, Integer> b) {
        super(program);
        this.a = a;
        this.b = b;
    }

    @Override
    public void execute() {
        Register<Integer> aReg = a.apply(program);
        int aVal = aReg.getValue();
        int bVal = b.apply(program);

        aVal = aVal & bVal;
        aReg.setValue(aVal);
    }

    public Function<Program, Register<Integer>> getA() {
        return a;
    }

    public Function<Program, Integer> getB() {
        return b;
    }
}
