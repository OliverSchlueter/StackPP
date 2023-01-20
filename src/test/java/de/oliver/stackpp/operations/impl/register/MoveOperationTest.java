package de.oliver.stackpp.operations.impl.register;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Machine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program(new Machine());
    }

    @Test
    void execute() {
        Operation operation = new MoveOperation(program, 0, p -> 42, p -> p.getMachine().getIntegerRegister("a"));
        operation.execute();

        assert program.getMachine().getIntegerRegister("a").getValue() == 42;
    }
}