package de.oliver.stackpp.operations.impl.bitwise;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.function.Function;

public class NotOperation extends Operation {

    private final Function<Program, Register<Integer>> a;

    public NotOperation(Program program, int line, Function<Program, Register<Integer>> a) {
        super(program, line);
        this.a = a;
    }

    @Override
    public void execute() {
        Register<Integer> aReg = a.apply(program);
        int aVal = aReg.getValue();

        aVal = ~aVal;
        aReg.setValue(aVal);
    }

    public Function<Program, Register<Integer>> getA() {
        return a;
    }
}
