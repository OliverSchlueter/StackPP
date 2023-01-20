package de.oliver.stackpp.virtualMachine.syscalls.impl;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;

public class ExitSyscall extends Syscall {

    public ExitSyscall(int id, Machine machine) {
        super(id, machine);
    }

    /**
     *  a - exit code
     */
    @Override
    public void execute(Program program) {
        int code = machine.getIntegerRegister("a").getValue();
        program.exit(code);
    }
}
