package de.oliver.stackpp.virtualMachine.syscalls.impl;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.operations.impl.SyscallOperation;
import de.oliver.stackpp.virtualMachine.Machine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeSyscallTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program(new Machine());
    }

    @Test
    void execute() {
        Operation operation = new SyscallOperation(program, 1, p -> 10);
        operation.execute();

        int curTime = (int) (System.currentTimeMillis() / 1000L);
        int time = program.getMachine().getStack().pop();

        assert time == curTime;
    }
}