package de.oliver.stackpp.operations;

import de.oliver.stackpp.Program;

import java.util.Queue;

public abstract class BlockOperation extends Operation {

    protected final Queue<Operation> operations;

    public BlockOperation(Program program, int line, Queue<Operation> operations) {
        super(program, line);
        this.operations = operations;
    }

    public abstract void addOperation(Operation operation);
    public Queue<Operation> getOperations() {
        return operations;
    }
}
