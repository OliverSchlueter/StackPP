package de.oliver.stackpp.operations.impl;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.utils.ExceptionHelper;

import java.util.function.Function;

public class ThrowOperation extends Operation {

    private final Function<Program, Integer> msgPtr;

    public ThrowOperation(Program program, int line, Function<Program, Integer> msgPtr) {
        super(program, line);
        this.msgPtr = msgPtr;
    }

    @Override
    public void execute() {
        int msgPtr = getMsgPtr().apply(program);

        String msg = "";

        int i = msgPtr;
        while (true){
            char c = (char) program.getMachine().getMemory().getAt(i);

            if(c == '\0'){
                break;
            }

            msg += c;

            i++;
        }

        ExceptionHelper.throwException(line, msg);
    }

    public Function<Program, Integer> getMsgPtr() {
        return msgPtr;
    }
}
