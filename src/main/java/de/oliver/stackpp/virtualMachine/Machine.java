package de.oliver.stackpp.virtualMachine;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.ExitSyscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.PrintSyscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.files.CloseFileSyscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.files.OpenFileSyscall;

import java.util.*;

public class Machine {

    private final Stack<Integer> stack;
    private final Map<String, Register<Integer>> registers;
    private final Map<Integer, Syscall> syscalls;
    private final Memory memory;
    private final Map<String, File> openedFiles; // path -> file

    public Machine() {
        this.stack = new Stack<>();
        this.registers = new HashMap<>();
        this.syscalls = new HashMap<>();
        this.memory = new Memory(1024);
        this.openedFiles = new HashMap<>();
        init();
    }

    private void init(){
        // registers for calculating
        registers.put("a", new Register<>("a", 0));
        registers.put("b", new Register<>("b", 0));
        registers.put("c", new Register<>("c", 0));
        registers.put("d", new Register<>("d", 0));
        registers.put("e", new Register<>("e", 0));

        // registers as indexes
        registers.put("i", new Register<>("i", 0));
        registers.put("j", new Register<>("j", 0));
        registers.put("k", new Register<>("k", 0));

        // registers for function inputs
        registers.put("f1", new Register<>("f1", 0));
        registers.put("f2", new Register<>("f2", 0));
        registers.put("f3", new Register<>("f3", 0));
        registers.put("f4", new Register<>("f4", 0));
        registers.put("f5", new Register<>("f5", 0));

        // syscalls
        syscalls.put(1, new ExitSyscall(1, this));
        syscalls.put(2, new PrintSyscall(2, this));
        syscalls.put(3, new OpenFileSyscall(3, this));
        syscalls.put(4, new CloseFileSyscall(4, this));
    }

    public void runProgram(Program program){
        int exitCode = program.run(this);
        System.out.println();
        System.out.println("Program finished with exit code " + exitCode);
        System.out.println();
    }

    public void resetStack(){
        stack.clear();
    }

    public void resetRegisters(){
        registers.clear();
    }

    public Stack<Integer> getStack() {
        return stack;
    }

    public Map<String, Register<Integer>> getRegisters() {
        return registers;
    }

    public Register<Integer> getRegister(String name){
        if(registers.containsKey(name)){
            return registers.get(name);
        }

        return null;
    }

    public Map<Integer, Syscall> getSyscalls() {
        return syscalls;
    }

    public Syscall getSyscall(int id){
        if(syscalls.containsKey(id)){
            return syscalls.get(id);
        }

        return null;
    }

    public Memory getMemory() {
        return memory;
    }

    public Map<String, File> getOpenedFiles() {
        return openedFiles;
    }
}
