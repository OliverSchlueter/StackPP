package de.oliver.stackpp.virtualMachine;


import de.oliver.stackpp.operations.*;
import de.oliver.stackpp.operations.impl.block.FunctionOperation;

import java.util.*;

public class Program {

    private Machine machine;
    private Map<String, FunctionOperation> functions;
    private final Queue<Operation> instructions;

    public Program(Machine machine) {
        this.machine = machine;
        this.instructions = new LinkedList<>();
        this.functions = new HashMap<>();
    }

    public Program(){
        this(null);
    }

    public void run(Machine m){
        this.machine = m;

        while (!instructions.isEmpty()) {
            Operation operation = instructions.poll();
            operation.execute();
        }
    }

    public Machine getMachine() {
        return machine;
    }

    public Map<String, FunctionOperation> getFunctions() {
        return functions;
    }
    public void addFunction(FunctionOperation functionOperation){
        functions.put(functionOperation.getName(), functionOperation);
    }

    public Queue<Operation> getInstructions() {
        return instructions;
    }
}
