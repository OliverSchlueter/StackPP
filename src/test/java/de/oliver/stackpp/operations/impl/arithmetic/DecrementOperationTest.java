package de.oliver.stackpp.operations.impl.arithmetic;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Machine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DecrementOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program(new Machine());
        program.getMachine().getIntegerRegister("a").setValue(43);
    }

    @Test
    void execute() {
        Operation operation = new DecrementOperation(program, 1, p -> p.getMachine().getIntegerRegister("a"));
        operation.execute();

        assert program.getMachine().getIntegerRegister("a").getValue() == 42;
    }
}