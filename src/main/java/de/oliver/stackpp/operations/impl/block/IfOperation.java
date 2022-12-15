package de.oliver.stackpp.operations.impl.block;

import de.oliver.stackpp.Token;
import de.oliver.stackpp.operations.BlockOperation;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Program;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

public class IfOperation extends BlockOperation {

    private final Function<Program, Integer> a;
    private final Function<Program, Integer> b;
    private final Token compareOperation;
    private final Queue<Operation> elseOperations;
    private boolean isInElse;

    public IfOperation(Program program, Function<Program, Integer> a, Function<Program, Integer> b, Token compareOperation) {
        super(program, new LinkedList<>());
        this.a = a;
        this.b = b;
        this.compareOperation = compareOperation;
        this.elseOperations = new LinkedList<>();
        this.isInElse = false;
    }

    @Override
    public void execute() {
        if(compare(program, a, b, compareOperation)){
            for (Operation operation : operations) {
                if(program.isExit()) return;
                operation.execute();
            }
        } else {
            for (Operation operation : elseOperations) {
                if(program.isExit()) return;
                operation.execute();
            }
        }
    }

    @Override
    public void addOperation(Operation operation){
        if(isInElse) {
            elseOperations.offer(operation);
        } else {
            operations.offer(operation);
        }
    }

    public Function<Program, Integer> getA() {
        return a;
    }

    public Function<Program, Integer> getB() {
        return b;
    }

    public Token getCompareOperation() {
        return compareOperation;
    }

    public Queue<Operation> getElseOperations() {
        return elseOperations;
    }

    public boolean isInElse() {
        return isInElse;
    }

    public void setInElse(boolean inElse) {
        isInElse = inElse;
    }

    public static boolean compare(Program program, Function<Program, Integer> a, Function<Program, Integer> b, Token compareOperation){
        int aVal = a.apply(program);
        int bVal = b.apply(program);

        boolean conditionTrue = false;

        switch (compareOperation){
            case EQUAL_SIGN -> {
                if(aVal == bVal) conditionTrue = true;
            }

            case GREATER_SIGN -> {
                if(aVal > bVal) conditionTrue = true;
            }

            case LOWER_SIGN -> {
                if(aVal < bVal) conditionTrue = true;
            }
        }
        return conditionTrue;
    }
}
