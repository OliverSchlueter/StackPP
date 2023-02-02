package de.oliver.stackpp.operations.impl.bitwise;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Machine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RightShiftOperationTest {

    private Program program;
    @BeforeEach
    void setUp() {
        program = new Program(new Machine());
        program.getMachine().getIntegerRegister("a").setValue(0b01000101);
        program.getMachine().getIntegerRegister("b").setValue(2);
    }

    @Test
    void execute() {
        Operation operation = new RightShiftOperation(program, 1, p -> p.getMachine().getIntegerRegister("a"),  p -> p.getMachine().getIntegerRegister("b").getValue());
        operation.execute();

        assert program.getMachine().getIntegerRegister("a").getValue() == 0b00010001;
    }
}