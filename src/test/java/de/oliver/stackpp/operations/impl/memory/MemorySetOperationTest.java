package de.oliver.stackpp.operations.impl.memory;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Machine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemorySetOperationTest {

    private Program program;

    @BeforeEach
    void setUp(){
        program = new Program(new Machine());
    }

    @Test
    void execute() {
        Operation operation = new MemorySetOperation(program, 0, p -> 7, p -> (byte)42);
        operation.execute();

        assert program.getMachine().getMemory().getAt(7) == 42;
    }
}