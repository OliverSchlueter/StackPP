package de.oliver.stackpp;


import de.oliver.stackpp.operations.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Program {

    public static final Class<Operation>[] supportedOperations = new Class[]{
            PushOperation.class,
            AddOperation.class,
            SubtractOperation.class,
            MultiplyOperation.class,
            DivideOperation.class,
            ModuloOperation.class,
            PopPrintOperation.class,
    };

    private final Stack<Integer> stack;
    private final Queue<Operation> instructions;

    public Program() {
        this.stack = new Stack<>();
        this.instructions = new LinkedList<>();
    }

    public void run(){
        while (!instructions.isEmpty()) {
            Operation operation = instructions.poll();
            operation.execute();
        }
    }

    public Stack<Integer> getStack() {
        return stack;
    }

    public Queue<Operation> getInstructions() {
        return instructions;
    }

    public Class<Operation>[] getSupportedOperations() {
        return supportedOperations;
    }
}
