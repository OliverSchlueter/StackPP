package de.oliver.stackpp.virtualMachine.syscalls.impl;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;

import java.util.Scanner;

public class ReadLineSyscall extends Syscall {

    private Scanner scanner;

    public ReadLineSyscall(int id, Machine machine) {
        super(id, machine);
        scanner = new Scanner(System.in);
    }

    @Override
    public void execute(Program program) {
        String input = scanner.nextLine();
        char[] chars = input.toCharArray();

        int ptr = machine.getMemory().allocate(chars.length + 1);
        machine.getStack().push(ptr);

        for (int i = 0; i < chars.length; i++) {
            machine.getMemory().setAt(ptr, (byte) chars[i]);
            ptr++;
        }

        machine.getMemory().setAt(ptr, (byte)0);
    }
}
