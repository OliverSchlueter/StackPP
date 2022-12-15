package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModuloOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program();
        program.getRegisters().get("a").setValue(11);
        program.getRegisters().get("b").setValue(5);
    }

    @Test
    void execute() {
        Operation operation = new ModuloOperation(program, p -> p.getRegisters().get("a"), p -> p.getRegisters().get("b").getValue());
        operation.execute(program);

        assert program.getRegisters().get("a").getValue() == 1;
    }
}