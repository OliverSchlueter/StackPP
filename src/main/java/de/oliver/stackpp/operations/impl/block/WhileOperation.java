package de.oliver.stackpp.operations.impl.block;

import de.oliver.stackpp.Token;
import de.oliver.stackpp.operations.BlockOperation;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.Program;

import java.util.LinkedList;
import java.util.function.Function;

public class WhileOperation extends BlockOperation {

    private final Function<Program, Integer> a;
    private final Function<Program, Integer> b;
    private final Token compareOperation;
    private boolean close;
    private boolean next;

    public WhileOperation(Program program, int line, Function<Program, Integer> a, Function<Program, Integer> b, Token compareOperation) {
        super(program, line, new LinkedList<>());
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
        close = false;
        next = false;
        while (IfOperation.compare(program, a, b, compareOperation) && !close) {
            for (Operation operation : operations) {
                if(program.isExit()) return;
                if(operation instanceof BreakOperation) {
                    return;
                } else if(next || operation instanceof ContinueOperation){
                    next = false;
                    break;
                }

                operation.execute();
            }
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

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }
}
