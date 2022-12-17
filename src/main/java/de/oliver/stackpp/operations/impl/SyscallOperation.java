package de.oliver.stackpp.operations.impl;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;

import java.util.function.Function;

public class SyscallOperation extends Operation {

    private final Function<Program, Integer> id;

    public SyscallOperation(Program program, Function<Program, Integer> id) {
        super(program);
        this.id = id;
    }

    @Override
    public void execute() {
        Syscall syscall = program.getMachine().getSyscall(id.apply(program));
        syscall.execute(program);
    }

    public Function<Program, Integer> getId() {
        return id;
    }
}
