package de.oliver.stackpp.operations.impl.arithmetic;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Machine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncrementOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program(new Machine());
        program.getMachine().getIntegerRegister("a").setValue(41);
    }

    @Test
    void execute() {
        Operation operation = new IncrementOperation(program, 1, p -> p.getMachine().getIntegerRegister("a"));
        operation.execute();

        assert program.getMachine().getIntegerRegister("a").getValue() == 42;
    }
}