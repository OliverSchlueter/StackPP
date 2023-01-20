package de.oliver.stackpp.operations.impl.stack;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.Program;

public class PrintStackOperation extends Operation {

    public PrintStackOperation(Program program, int line) {
        super(program, line);
    }

    @Override
    public void execute() {
        for (int i = program.getMachine().getStack().size()-1; i >= 0; i--) {
            System.out.println(program.getMachine().getStack().get(i));
        }
    }
}
