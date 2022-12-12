package de.oliver.stackpp.operations;

import de.oliver.stackpp.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DivideOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program();
        program.getStack().push(8);
        program.getStack().push(4);
    }

    @Test
    void execute() {
        Operation operation = new DivideOperation(program);
        operation.execute();

        assert program.getStack().pop() == 2;
    }
}