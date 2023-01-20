package de.oliver.stackpp.operations.impl.stack;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Machine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PushOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program(new Machine());
    }

    @Test
    void execute() {
        Operation operation = new PushOperation(program, 0, p -> 5);
        operation.execute();

        assert program.getMachine().getStack().pop() == 5;
    }
}