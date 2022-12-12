package de.oliver.stackpp.operations;

import de.oliver.stackpp.Program;

public class AddOperation extends Operation{


    public AddOperation(Program program) {
        super(program);
    }

    @Override
    public void execute() {
        int a = program.getStack().pop();
        int b = program.getStack().pop();
        int result = a + b;

        program.getStack().push(result);
    }
}
