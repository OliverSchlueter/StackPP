package de.oliver.stackpp.operations.impl.block;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;

import java.util.function.Function;

public class BreakOperation extends Operation {

    private final Function<Program, WhileOperation> whileBlock;

    public BreakOperation(Program program, int line, Function<Program, WhileOperation> whileBlock) {
        super(program, line);
        this.whileBlock = whileBlock;
    }

    @Override
    public void execute() {
        whileBlock.apply(program).setClose(true);
    }

    public Function<Program, WhileOperation> getWhileBlock() {
        return whileBlock;
    }
}
