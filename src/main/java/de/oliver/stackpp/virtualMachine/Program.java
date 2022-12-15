package de.oliver.stackpp.virtualMachine;


import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.operations.impl.block.FunctionOperation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Program {

    private Machine machine;
    private Map<String, FunctionOperation> functions;
    private final Queue<Operation> instructions;
    private boolean exit;
    private int exitCode;

    public Program(Machine machine) {
        this.machine = machine;
        this.instructions = new LinkedList<>();
        this.functions = new HashMap<>();
        this.exit = false;
        this.exitCode = 0;
    }

    public Program(){
        this(null);
    }

    public int run(Machine m){
        this.machine = m;

        this.exit = false;
        exitCode = 0;

        while (!exit && !instructions.isEmpty()) {
            Operation operation = instructions.poll();
            operation.execute();
        }

        return exitCode;
    }

    public void exit(int exitCode){
        this.exitCode = exitCode;
        this.exit = true;
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

    public boolean isExit() {
        return exit;
    }
}
