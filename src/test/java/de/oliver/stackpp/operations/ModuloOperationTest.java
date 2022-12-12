package de.oliver.stackpp.operations;

import de.oliver.stackpp.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModuloOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program();
        program.getStack().push(11);
        program.getStack().push(5);
    }

    @Test
    void execute() {
        Operation operation = new ModuloOperation(program);
        operation.execute();

        assert program.getStack().pop() == 1;
    }
}