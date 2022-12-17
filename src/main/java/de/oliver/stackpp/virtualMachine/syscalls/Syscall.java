package de.oliver.stackpp.virtualMachine.syscalls;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.Machine;

public abstract class Syscall {

    protected final int id;
    protected final Machine machine;

    public Syscall(int id, Machine machine) {
        this.id = id;
        this.machine = machine;
    }

    public abstract void execute(Program program);

    public int getId() {
        return id;
    }

    public Machine getMachine() {
        return machine;
    }
}
