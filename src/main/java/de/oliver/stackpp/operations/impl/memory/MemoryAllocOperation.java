package de.oliver.stackpp.operations.impl.memory;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.Program;
import de.oliver.stackpp.utils.ExceptionHelper;

import java.util.function.Function;

public class MemoryAllocOperation extends Operation {

    private final Function<Program, Integer> size;

    public MemoryAllocOperation(Program program, int line, Function<Program, Integer> size) {
        super(program, line);
        this.size = size;
    }

    @Override
    public void execute() {
        int sizeVal = size.apply(program);
        if(sizeVal <= 0){
            ExceptionHelper.throwException(line, "Size must be greater than 0");
            program.exit(1);
            return;
        }

        int ptr = program.getMachine().getMemory().allocate(sizeVal);
        program.getMachine().getStack().push(ptr);
    }

    public Function<Program, Integer> getSize() {
        return size;
    }
}
