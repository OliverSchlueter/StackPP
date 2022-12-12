package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;
import de.oliver.stackpp.virtualMachine.Register;

public class PrintOperation extends Operation{

    private final String registerName;

    public PrintOperation(Program program, String registerName) {
        super(program);
        this.registerName = registerName;
    }

    @Override
    public void execute() {
        Register<Integer> register = program.getRegisters().get(registerName);

        System.out.println(register.getValue());
    }

    public String getRegisterName() {
        return registerName;
    }
}
