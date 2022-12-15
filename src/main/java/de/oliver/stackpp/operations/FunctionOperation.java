package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;

import java.util.LinkedList;

public class FunctionOperation extends BlockOperation implements CompileOperation{

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
    public void execute(Program program) {

    }

    public void run(Program program){
        for (Operation operation : operations) {
            operation.execute(program);
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
