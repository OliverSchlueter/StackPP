package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubtractOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program();
        program.getRegisters().get("a").setValue(6);
        program.getRegisters().get("b").setValue(2);
    }

    @Test
    void execute() {
        Operation operation = new SubtractOperation(program, "a", "b");
        operation.execute();

        assert program.getRegisters().get("a").getValue() == 4;
    }
}