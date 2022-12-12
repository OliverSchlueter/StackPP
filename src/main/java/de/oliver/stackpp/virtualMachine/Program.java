package de.oliver.stackpp.virtualMachine;


import de.oliver.stackpp.operations.*;

import java.util.*;

public class Program {

    private Stack<Integer> stack;
    private Map<String, Register<Integer>> registers;
    private final Queue<Operation> instructions;

    public Program() {
        this.stack = new Stack<>();
        this.registers = new HashMap<>();
        this.instructions = new LinkedList<>();

        clearData();
    }

    public void run(){
        clearData();

        while (!instructions.isEmpty()) {
            Operation operation = instructions.poll();
            operation.execute();
        }
    }

    private void clearData(){
        stack.clear();
        registers.clear();

        registers.put("a", new Register<>("a", 0));
        registers.put("b", new Register<>("b", 0));
        registers.put("c", new Register<>("c", 0));
    }

    public Stack<Integer> getStack() {
        return stack;
    }

    public Map<String, Register<Integer>> getRegisters() {
        return registers;
    }

    public Queue<Operation> getInstructions() {
        return instructions;
    }
}
