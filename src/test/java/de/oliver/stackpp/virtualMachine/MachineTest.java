package de.oliver.stackpp.virtualMachine;

import de.oliver.stackpp.virtualMachine.syscalls.Syscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.ExitSyscall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MachineTest {

    private Machine machine;

    @BeforeEach
    void setUp() {
        machine = new Machine();
    }

    @Test
    void getSyscall() {
        Syscall syscall = machine.getSyscall(1);
        assert syscall != null;
        assert syscall instanceof ExitSyscall;
        assert syscall.getId() == 1;
    }

    @Test
    void getSyscallByClass() {
        Syscall syscall = machine.getSyscall(ExitSyscall.class);
        assert syscall != null;
        assert syscall instanceof ExitSyscall;
        assert syscall.getId() == 1;
    }
}