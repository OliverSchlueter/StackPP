package de.oliver.stackpp.operations.impl.bitwise;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.function.Function;

public class RightShiftOperation extends Operation {

    private final Function<Program, Register<Integer>> a;
    private final Function<Program, Integer> amount;

    public RightShiftOperation(Program program, Function<Program, Register<Integer>> a, Function<Program, Integer> amount) {
        super(program);
        this.a = a;
        this.amount = amount;
    }

    @Override
    public void execute() {
        Register<Integer> aReg = a.apply(program);
        int aVal = aReg.getValue();
        int amountVal = amount.apply(program);
        aVal = aVal >> amountVal;
        aReg.setValue(aVal);
    }

    public Function<Program, Register<Integer>> getA() {
        return a;
    }

    public Function<Program, Integer> getAmount() {
        return amount;
    }
}
