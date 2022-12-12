package de.oliver.stackpp.operations;

import de.oliver.stackpp.virtualMachine.Program;
import de.oliver.stackpp.virtualMachine.Register;

public class PushOperation extends Operation{

    private int value;
    private String registerName;

    public PushOperation(Program program, int value) {
        super(program);
        this.value = value;
        this.registerName = null;
    }

    public PushOperation(Program program, String registerName){
        super(program);
        this.registerName = registerName;
    }

    @Override
    public void execute() {
        if(registerName == null) {
            program.getStack().push(value);
        } else {
            Register<Integer> register = program.getRegisters().get(registerName);
            program.getStack().push(register.getValue());
        }
    }

    public int getValue() {
        return value;
    }

    public String getRegisterName() {
        return registerName;
    }
}
