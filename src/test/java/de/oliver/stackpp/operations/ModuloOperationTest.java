package de.oliver.stackpp.operations;

import de.oliver.stackpp.operations.impl.arithmetic.ModuloOperation;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModuloOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program(new Machine());
        program.getMachine().getRegisters().get("a").setValue(11);
        program.getMachine().getRegisters().get("b").setValue(5);
    }

    @Test
    void execute() {
        Operation operation = new ModuloOperation(program, p -> program.getMachine().getRegister("a"), p -> program.getMachine().getRegister("b").getValue());
        operation.execute();

        assert program.getMachine().getRegisters().get("a").getValue() == 1;
    }
}