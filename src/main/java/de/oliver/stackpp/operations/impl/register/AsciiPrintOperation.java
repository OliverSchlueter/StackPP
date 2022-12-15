package de.oliver.stackpp.operations.impl.register;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Program;

import java.util.function.Function;

public class AsciiPrintOperation extends Operation {

    private final Function<Program, Integer> charr;

    public AsciiPrintOperation(Program program, Function<Program, Integer> charr) {
        super(program);
        this.charr = charr;
    }

    @Override
    public void execute() {
        System.out.print((char) charr.apply(program).intValue());
    }

    public Function<Program, Integer> getCharr() {
        return charr;
    }
}
