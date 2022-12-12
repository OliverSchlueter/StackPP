package de.oliver.stackpp.operations;

import de.oliver.stackpp.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program();
        program.getStack().push(3);
        program.getStack().push(7);
    }

    @Test
    void execute() {
        Operation operation = new AddOperation(program);
        operation.execute();

        assert program.getStack().pop() == 10;
    }
}