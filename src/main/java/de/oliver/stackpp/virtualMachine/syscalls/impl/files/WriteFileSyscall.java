package de.oliver.stackpp.virtualMachine.syscalls.impl.files;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileSyscall extends Syscall {

    public WriteFileSyscall(int id, Machine machine) {
        super(id, machine);
    }

    /**
     *  a - pointer to a null-terminated string that represents the path of the file
     *  b - pointer to the data to write
     *  c - size of the data
     *  d - offset in file
     */
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

        java.io.File f = new java.io.File(path);
        if(!f.exists()){
            return;
        }

        int dataPtr = machine.getRegister("b").getValue();
        int dataSize = machine.getRegister("c").getValue();
        int offset = machine.getRegister("d").getValue();

        char[] data = new char[dataSize];

        for (int j = 0; j < dataSize; j++) {
            data[j] = (char) machine.getMemory().getAt(dataPtr + j);
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write(data, offset, dataSize);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
