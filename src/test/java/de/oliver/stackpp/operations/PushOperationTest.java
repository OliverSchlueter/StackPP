package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PushOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program();
    }

    @Test
    void execute() {
        Operation operation = new PushOperation(program, p -> 5);
        operation.execute();

        assert program.getStack().size() > 0 && program.getStack().pop() == 5;
    }
}