package de.oliver.stackpp.operations;

import de.oliver.stackpp.Program;

public class PopPrintOperation extends Operation{

    public PopPrintOperation(Program program) {
        super(program);
    }

    @Override
    public void execute() {
        int a = program.getStack().pop();

        System.out.println(a);

    }
}
