package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;

public class CallOperation extends Operation{

    private final String functionName;

    public CallOperation(Program program, String functionName) {
        super(program);
        this.functionName = functionName;
    }

    @Override
    public void execute(Program program) {
        FunctionOperation function = program.getFunctions().get(functionName);
        function.run(program);
    }

    public String getFunctionName() {
        return functionName;
    }
}
