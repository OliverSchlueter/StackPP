package de.oliver.stackpp.virtualMachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Machine {

    private final Stack<Integer> stack;
    private final Map<String, Register<Integer>> registers;
    private final Memory memory;

    public Machine() {
        this.stack = new Stack<>();
        this.registers = new HashMap<>();
        this.memory = new Memory(1024);

        init();
    }

    private void init(){
        registers.put("a", new Register<>("a", 0));
        registers.put("b", new Register<>("b", 0));
        registers.put("c", new Register<>("c", 0));
        registers.put("d", new Register<>("d", 0));
        registers.put("e", new Register<>("e", 0));
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

    public Memory getMemory() {
        return memory;
    }
}
