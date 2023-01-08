package de.oliver.stackpp.virtualMachine.syscalls.impl.graphics;

import de.oliver.gameEngine.Window;
import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;

public class ShowWindowSyscall extends Syscall {

    public ShowWindowSyscall(int id, Machine machine) {
        super(id, machine);
    }

    @Override
    public void execute(Program program) {
        Window.get().run();
    }
}
