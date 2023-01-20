package de.oliver.stackpp.operations.impl.arithmetic;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.Program;
import de.oliver.stackpp.utils.ExceptionHelper;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.function.Function;

public class DivideOperation extends Operation {

    private final Function<Program, Register<Integer>> a;
    private final Function<Program, Integer> b;

    public DivideOperation(Program program, int line, Function<Program, Register<Integer>> a, Function<Program, Integer> b) {
        super(program, line);
        this.a = a;
        this.b = b;
    }

    @Override
    public void execute() {
        Register<Integer> aReg = a.apply(program);
        int bVal = b.apply(program);

        if(bVal == 0){
            ExceptionHelper.throwException(line, "Dividing by 0 is not allowed");
            program.exit(1);
            return;
        }

        int result = aReg.getValue() / bVal;

        aReg.setValue(result);
    }

    public Function<Program, Register<Integer>> getA() {
        return a;
    }

    public Function<Program, Integer> getB() {
        return b;
    }
}
