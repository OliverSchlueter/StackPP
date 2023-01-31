package de.oliver.stackpp.operations.impl.block;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;

import java.util.function.Function;

public class ContinueOperation extends Operation {

    private final Function<Program, WhileOperation> whileBlock;

    public ContinueOperation(Program program, int line, Function<Program, WhileOperation> whileBlock) {
        super(program, line);
        this.whileBlock = whileBlock;
    }

    @Override
    public void execute() {
        whileBlock.apply(program).setNext(true);
    }

    public Function<Program, WhileOperation> getWhileBlock() {
        return whileBlock;
    }
}
