package de.oliver.stackpp.operations.impl.memory;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Machine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryGetOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program(new Machine());
        program.getMachine().getMemory().setAt(3, (byte) 71);
    }

    @Test
    void execute() {
        Operation operation = new MemoryGetOperation(program, 0, p -> 3);
        operation.execute();

        assert program.getMachine().getStack().pop() == 71;
    }
}