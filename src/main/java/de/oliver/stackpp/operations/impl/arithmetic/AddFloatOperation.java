package de.oliver.stackpp.operations.impl.arithmetic;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.function.Function;

public class AddFloatOperation extends Operation {

    private final Function<Program, Register<Float>> a;
    private final Function<Program, Float> b;

    public AddFloatOperation(Program program, int line, Function<Program, Register<Float>> a, Function<Program, Float> b) {
        super(program, line);
        this.a = a;
        this.b = b;
    }

    @Override
    public void execute() {
        Register<Float> aReg = a.apply(program);
        float bVal = b.apply(program);

        float result = aReg.getValue() + bVal;

        aReg.setValue(result);
    }

    public Function<Program, Register<Float>> getA() {
        return a;
    }

    public Function<Program, Float> getB() {
        return b;
    }
}
