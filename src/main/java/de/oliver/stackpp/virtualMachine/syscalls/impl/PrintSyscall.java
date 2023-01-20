package de.oliver.stackpp.virtualMachine.syscalls.impl;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;

import java.io.PrintStream;

public class PrintSyscall extends Syscall {

    public PrintSyscall(int id, Machine machine) {
        super(id, machine);
    }

    /**
     *  a - file
     *  b - pointer to the start of the buffer
     *  c - size of the buffer
     */
    @Override
    public void execute(Program program) {
        int file = machine.getIntegerRegister("a").getValue();
        int buffer = machine.getIntegerRegister("b").getValue();
        int size = machine.getIntegerRegister("c").getValue();

        PrintStream ps = null;

        switch (file){
            case 1 -> ps = System.out;
            case 2 -> ps = System.err;
        }

        if(ps == null){
            return;
        }

        for (int ptr = buffer; ptr < buffer+size; ptr++) {
            ps.print((char) machine.getMemory().getAt(ptr));
        }
    }
}
