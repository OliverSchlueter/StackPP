package de.oliver.stackpp.operations.impl.block;

import de.oliver.stackpp.operations.BlockOperation;
import de.oliver.stackpp.operations.CompileOperation;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Program;

import java.util.LinkedList;

public class FunctionOperation extends BlockOperation implements CompileOperation {

    private final String name;

    public FunctionOperation(Program program, String name) {
        super(program, new LinkedList<>());
        this.name = name;
    }

    @Override
    public void executeOnCompile(Program program) {
        program.addFunction(this);
    }

    @Override
    public void execute() {

    }

    public void run(){
        for (Operation operation : operations) {
            if(program.isExit()) return;
            operation.execute();
        }
    }

    @Override
    public void addOperation(Operation operation) {
        this.operations.offer(operation);
    }

    public String getName() {
        return name;
    }
}
