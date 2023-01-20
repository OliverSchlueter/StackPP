package de.oliver.stackpp.operations.impl.block;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.Program;
import de.oliver.stackpp.utils.ExceptionHelper;

public class CallOperation extends Operation {

    private final String functionName;

    public CallOperation(Program program, int line, String functionName) {
        super(program, line);
        this.functionName = functionName;
    }

    @Override
    public void execute() {
        FunctionOperation function = program.getFunctions().get(functionName);
        if(function == null){
            ExceptionHelper.throwException(line, "Could not find function with name: '" + functionName + "'");
            program.exit(1);
            return;
        }
        function.run();
    }

    public String getFunctionName() {
        return functionName;
    }
}
