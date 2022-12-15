package de.oliver.stackpp.operations;

import de.oliver.stackpp.operations.impl.stack.PushOperation;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PushOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program(new Machine());
    }

    @Test
    void execute() {
        Operation operation = new PushOperation(program, p -> 5);
        operation.execute();

        assert program.getMachine().getStack().size() > 0 && program.getMachine().getStack().pop() == 5;
    }
}