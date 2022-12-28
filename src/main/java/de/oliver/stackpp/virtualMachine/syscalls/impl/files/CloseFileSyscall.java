package de.oliver.stackpp.virtualMachine.syscalls.impl.files;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.File;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;

public class CloseFileSyscall extends Syscall {

    public CloseFileSyscall(int id, Machine machine) {
        super(id, machine);
    }

    @Override
    public void execute(Program program) {
        int pathPtr = machine.getRegister("a").getValue();
        String path = "";

        int i = pathPtr;
        while (true){
            char c = (char) machine.getMemory().getAt(i);


            if(c == '\0'){
                break;
            }

            path += c;

            i++;
        }

        if (!machine.getOpenedFiles().containsKey(path)) {
            return;
        }

        File file = machine.getOpenedFiles().get(path);
        machine.getMemory().free(file.getDataPtr());
        machine.getOpenedFiles().remove(path);
    }
}
