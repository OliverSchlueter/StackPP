package de.oliver.stackpp.operations.impl.arithmetic;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.function.Function;

public class DecrementOperation extends Operation {

    private final Function<Program, Register<Integer>> a;

    public DecrementOperation(Program program, int line, Function<Program, Register<Integer>> a) {
        super(program, line);
        this.a = a;
    }

    @Override
    public void execute() {
        Register<Integer> aReg = a.apply(program);
        aReg.setValue(aReg.getValue() - 1);
    }

    public Function<Program, Register<Integer>> getA() {
        return a;
    }
}
