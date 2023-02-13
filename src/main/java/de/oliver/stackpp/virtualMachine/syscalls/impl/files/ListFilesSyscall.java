package de.oliver.stackpp.virtualMachine.syscalls.impl.files;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.utils.ExceptionHelper;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;

import java.io.File;

public class ListFilesSyscall extends Syscall {

    public ListFilesSyscall(int id, Machine machine) {
        super(id, machine);
    }

    /**
     *  'a' - pointer to a path to a folder
     */
    @Override
    public void execute(Program program) {
        int folderPtr = machine.getIntegerRegister("a").getValue();
        String folderName = "";

        while (true){
            char c = (char) machine.getMemory().getAt(folderPtr);


            if(c == '\0'){
                break;
            }

            folderName  += c;

            folderPtr++;
        }

        File folder = new File(folderName);

        if(!folder.exists() || !folder.isDirectory()){
            ExceptionHelper.throwException(-1, "Could not find folder: '" + folderName + "'");
            return;
        }

        File[] files = folder.listFiles();

        for (File file : files) {
            String fileName = file.getName() + "\0";
            char[] chars = fileName.toCharArray();

            final int ptr = machine.getMemory().allocate(chars.length);
            for (int i = 0; i < chars.length; i++) {
                machine.getMemory().setAt(ptr + i, (byte) chars[i]);
            }

            machine.getStack().push(ptr);
        }

        machine.getStack().push(files.length);
    }
}
