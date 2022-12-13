package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

public class IfOperation extends Operation{

    private final Function<Program, Integer> a;
    private final Function<Program, Integer> b;
    private final Queue<Operation> operations;
    private final Queue<Operation> elseOperations;
    private boolean isInElse;

    public IfOperation(Program program, Function<Program, Integer> a, Function<Program, Integer> b) {
        super(program);
        this.a = a;
        this.b = b;
        this.operations = new LinkedList<>();
        this.elseOperations = new LinkedList<>();
        this.isInElse = false;
    }

    @Override
    public void execute() {
        int aVal = a.apply(program);
        int bVal = b.apply(program);

        if(aVal == bVal){
            for (Operation operation : operations) {
                operation.execute();
            }
        } else {
            for (Operation operation : elseOperations) {
                operation.execute();
            }
        }
    }

    public Queue<Operation> getOperations() {
        return operations;
    }

    public Queue<Operation> getElseOperations() {
        return elseOperations;
    }

    public void addOperation(Operation operation){
        if(isInElse) {
            elseOperations.offer(operation);
        } else {
            operations.offer(operation);
        }
    }

    public void setInElse(boolean inElse) {
        isInElse = inElse;
    }
}
