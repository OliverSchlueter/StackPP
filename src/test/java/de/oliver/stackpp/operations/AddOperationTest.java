package de.oliver.stackpp.operations;

import de.oliver.stackpp.operations.impl.arithmetic.AddOperation;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program(new Machine());
        program.getMachine().getRegisters().get("a").setValue(3);
        program.getMachine().getRegisters().get("b").setValue(7);
    }

    @Test
    void execute() {
        Operation operation = new AddOperation(program, p -> program.getMachine().getRegister("a"), p -> program.getMachine().getRegister("b").getValue());
        operation.execute();

        assert program.getMachine().getRegisters().get("a").getValue() == 10;
    }
}