package de.oliver.stackpp.virtualMachine;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.ExitSyscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.PrintSyscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.ReadLineSyscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.TimeSyscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.files.CloseFileSyscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.files.OpenFileSyscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.files.WriteFileSyscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.graphics.CreateSpriteSyscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.graphics.EditSpriteSyscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.graphics.GetSpritePropertiesSyscall;
import de.oliver.stackpp.virtualMachine.syscalls.impl.graphics.ShowWindowSyscall;

import java.util.*;

public class Machine {

    private final Stack<Integer> stack;
    private final Map<String, Register<Integer>> intergerRegisters;
    private final Map<String, Register<Float>> floatRegisters;
    private final Map<Integer, Syscall> syscalls;
    private final Memory memory;
    private final Map<String, File> openedFiles; // path -> file

    public Machine() {
        this.stack = new Stack<>();
        this.intergerRegisters = new HashMap<>();
        this.floatRegisters = new HashMap<>();
        this.syscalls = new HashMap<>();
        this.memory = new Memory(640_000); // should be enough for everyone
        this.openedFiles = new HashMap<>();
        init();
    }

    private void init(){
        // registers for calculating
        intergerRegisters.put("a", new Register<>("a", 0));
        intergerRegisters.put("b", new Register<>("b", 0));
        intergerRegisters.put("c", new Register<>("c", 0));
        intergerRegisters.put("d", new Register<>("d", 0));
        intergerRegisters.put("e", new Register<>("e", 0));
        intergerRegisters.put("f", new Register<>("f", 0));

        // registers as indexes
        intergerRegisters.put("i", new Register<>("i", 0));
        intergerRegisters.put("j", new Register<>("j", 0));
        intergerRegisters.put("k", new Register<>("k", 0));

        // registers for function inputs
        intergerRegisters.put("f1", new Register<>("f1", 0));
        intergerRegisters.put("f2", new Register<>("f2", 0));
        intergerRegisters.put("f3", new Register<>("f3", 0));
        intergerRegisters.put("f4", new Register<>("f4", 0));
        intergerRegisters.put("f5", new Register<>("f5", 0));
        intergerRegisters.put("f6", new Register<>("f6", 0));
        intergerRegisters.put("f7", new Register<>("f7", 0));
        intergerRegisters.put("f8", new Register<>("f8", 0));

        // syscalls
        syscalls.put(1, new ExitSyscall(1, this));

        syscalls.put(2, new PrintSyscall(2, this));

        syscalls.put(3, new OpenFileSyscall(3, this));
        syscalls.put(4, new CloseFileSyscall(4, this));
        syscalls.put(5, new WriteFileSyscall(5, this));

        syscalls.put(6, new ShowWindowSyscall(6, this));
        syscalls.put(7, new CreateSpriteSyscall(7, this));
        syscalls.put(8, new EditSpriteSyscall(8, this));
        syscalls.put(9, new GetSpritePropertiesSyscall(9, this));

        syscalls.put(10, new TimeSyscall(10, this));
        syscalls.put(11, new ReadLineSyscall(11, this));
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
        intergerRegisters.clear();
        floatRegisters.clear();
    }

    public Stack<Integer> getStack() {
        return stack;
    }

    public Map<String, Register<Integer>> getIntergerRegisters() {
        return intergerRegisters;
    }

    public Map<String, Register<Float>> getFloatRegisters() {
        return floatRegisters;
    }

    /**
     * @deprecated use getIntegerRegister and getFloatRegister instead
     */
    @Deprecated
    public Register<Integer> getRegister(String name){
        return getIntegerRegister(name);
    }

    public Register<Integer> getIntegerRegister(String name){
        return intergerRegisters.getOrDefault(name, null);
    }

    public Register<Float> getFloatRegister(String name){
        return floatRegisters.getOrDefault(name, null);
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
