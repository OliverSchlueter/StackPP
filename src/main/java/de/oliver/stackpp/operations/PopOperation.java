package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;
import de.oliver.stackpp.virtualMachine.Register;

public class PopOperation extends Operation{

    private final String registerName;

    public PopOperation(Program program, String registerName) {
        super(program);
        this.registerName = registerName;
    }

    @Override
    public void execute() {
        Register<Integer> register = program.getRegisters().get(registerName);

        register.setValue(program.getStack().pop());
    }

    public String getRegisterName() {
        return registerName;
    }
}
