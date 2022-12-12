package de.oliver.stackpp.operations;

import de.oliver.stackpp.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MultiplyOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program();
        program.getStack().push(5);
        program.getStack().push(3);
    }

    @Test
    void execute() {
        Operation operation = new MultiplyOperation(program);
        operation.execute();

        assert program.getStack().pop() == 15;
    }
}