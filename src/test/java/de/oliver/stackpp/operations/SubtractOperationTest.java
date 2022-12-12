package de.oliver.stackpp.operations;

import de.oliver.stackpp.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtractOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program();
        program.getStack().push(6);
        program.getStack().push(2);
    }

    @Test
    void execute() {
        Operation operation = new SubtractOperation(program);
        operation.execute();

        assert program.getStack().pop() == 4;
    }
}