package de.oliver.stackpp.operations.impl.block;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.Program;

public class CallOperation extends Operation {

    private final String functionName;

    public CallOperation(Program program, String functionName) {
        super(program);
        this.functionName = functionName;
    }

    @Override
    public void execute() {
        FunctionOperation function = program.getFunctions().get(functionName);
        function.run();
    }

    public String getFunctionName() {
        return functionName;
    }
}
