package de.oliver.stackpp.virtualMachine.syscalls.impl.files;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.File;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class OpenFileSyscall extends Syscall {

    public OpenFileSyscall(int id, Machine machine) {
        super(id, machine);
    }

    /**
     *  a - pointer to a null-terminated string that represents the path of the file
     */
    @Override
    public void execute(Program program) {
        int pathPtr = machine.getIntegerRegister("a").getValue();
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

        java.io.File f = new java.io.File(path);
        if(!f.exists()){
            return;
        }

        int fileSize = (int)f.length();
        byte[] data;
        try {
            data = Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int dataPtr = machine.getMemory().allocate(fileSize);

        for (int j = 0; j < fileSize; j++) {
            machine.getMemory().setAt(j + dataPtr, data[j]);
        }

        File file = new File(f.getName(), path, dataPtr, fileSize);
        machine.getOpenedFiles().put(path, file);

        machine.getStack().push(fileSize);
        machine.getStack().push(dataPtr);
    }
}
