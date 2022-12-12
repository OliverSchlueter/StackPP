package de.oliver.stackpp.operations;

import de.oliver.stackpp.Program;

public class DivideOperation extends Operation{

    public DivideOperation(Program program) {
        super(program);
    }

    @Override
    public void execute() {
        int a = program.getStack().pop();
        int b = program.getStack().pop();
        int result = b / a;

        program.getStack().push(result);
    }
}
