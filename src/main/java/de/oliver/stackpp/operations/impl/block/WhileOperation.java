package de.oliver.stackpp.operations.impl.block;

import de.oliver.stackpp.Token;
import de.oliver.stackpp.operations.BlockOperation;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Program;

import java.util.LinkedList;
import java.util.function.Function;

public class WhileOperation extends BlockOperation {

    private final Function<Program, Integer> a;
    private final Function<Program, Integer> b;
    private final Token compareOperation;

    public WhileOperation(Program program, Function<Program, Integer> a, Function<Program, Integer> b, Token compareOperation) {
        super(program, new LinkedList<>());
        this.a = a;
        this.b = b;
        this.compareOperation = compareOperation;
    }

    @Override
    public void addOperation(Operation operation) {
        operations.offer(operation);
    }

    @Override
    public void execute() {
        while (IfOperation.compare(program, a, b, compareOperation)) {
            for (Operation operation : operations) {
                operation.execute();
            }
        }
    }
}
