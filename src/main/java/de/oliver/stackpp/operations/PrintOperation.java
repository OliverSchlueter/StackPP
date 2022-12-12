package de.oliver.stackpp.operations;

import de.oliver.stackpp.Program;

public class PrintOperation extends Operation{

    public PrintOperation(Program program) {
        super(program);
    }

    @Override
    public void execute() {
        int a = program.getStack().lastElement();

        System.out.println(a);
    }
}
