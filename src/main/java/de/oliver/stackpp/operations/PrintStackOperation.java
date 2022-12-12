package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;

public class PrintStackOperation extends Operation{

    public PrintStackOperation(Program program) {
        super(program);
    }

    @Override
    public void execute() {
        for (int i = program.getStack().size()-1; i >= 0; i--) {
            System.out.println(program.getStack().get(i));
        }
    }
}
