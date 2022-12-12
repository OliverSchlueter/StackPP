package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;
import de.oliver.stackpp.virtualMachine.Register;

public class MoveOperation extends Operation{

    private final int value;
    private final String registerName;

    public MoveOperation(Program program, int value, String registerName) {
        super(program);
        this.value = value;
        this.registerName = registerName;
    }

    @Override
    public void execute() {
        Register<Integer> register = program.getRegisters().get(registerName);

        register.setValue(value);
    }

    public int getValue() {
        return value;
    }

    public String getRegisterName() {
        return registerName;
    }
}
