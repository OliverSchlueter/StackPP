package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;
import de.oliver.stackpp.virtualMachine.Register;

public class ModuloOperation extends Operation{

    private final String registerNameA;
    private final String registerNameB;

    public ModuloOperation(Program program, String registerNameA, String registerNameB) {
        super(program);
        this.registerNameA = registerNameA;
        this.registerNameB = registerNameB;
    }

    @Override
    public void execute() {
        Register<Integer> registerA = program.getRegisters().get(registerNameA);
        Register<Integer> registerB = program.getRegisters().get(registerNameB);

        int result = registerA.getValue() % registerB.getValue();

        registerA.setValue(result);
    }
}
