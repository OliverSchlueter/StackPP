package de.oliver.stackpp.virtualMachine.syscalls.impl;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;

public class TimeSyscall extends Syscall {

    public TimeSyscall(int id, Machine machine) {
        super(id, machine);
    }

    /**
     *  in seconds
     */
    @Override
    public void execute(Program program) {
        int currentTime = (int)(System.currentTimeMillis() / 1000L);
        machine.getStack().push(currentTime);
    }
}
