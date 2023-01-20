package de.oliver.stackpp.operations.impl.arithmetic;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program(new Machine());
        program.getMachine().getIntegerRegister("a").setValue(3);
        program.getMachine().getIntegerRegister("b").setValue(7);
    }

    @Test
    void execute() {
        Operation operation = new AddOperation(program, 1, p -> program.getMachine().getIntegerRegister("a"), p -> program.getMachine().getIntegerRegister("b").getValue());
        operation.execute();

        assert program.getMachine().getIntegerRegister("a").getValue() == 10;
    }
}