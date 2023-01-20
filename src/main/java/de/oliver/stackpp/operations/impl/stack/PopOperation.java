package de.oliver.stackpp.operations.impl.stack;

import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.Program;
import de.oliver.stackpp.utils.ExceptionHelper;
import de.oliver.stackpp.virtualMachine.Register;

import java.util.function.Function;

public class PopOperation extends Operation {

    private final Function<Program, Register<Integer>> register;

    public PopOperation(Program program, int line, Function<Program, Register<Integer>> register) {
        super(program, line);
        this.register = register;
    }

    @Override
    public void execute() {
        if(program.getMachine().getStack().isEmpty()){
            ExceptionHelper.throwException(line, "Can not pop, the stack is empty");
            program.exit(1);
            return;
        }

        register.apply(program).setValue(program.getMachine().getStack().pop());
    }

    public Function<Program, Register<Integer>> getRegister() {
        return register;
    }
}
