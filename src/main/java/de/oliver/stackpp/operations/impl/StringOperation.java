package de.oliver.stackpp.operations.impl;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;

public class StringOperation extends Operation {

    private final String[] str;

    public StringOperation(Program program, String[] str) {
        super(program);
        this.str = str;
    }

    @Override
    public void execute() {
        String string = String.join(" ", str);

        if(!string.endsWith("\0")){
            string += "\0";
        }

        char[] chars = string.toCharArray();

        int length = chars.length;
        int ptr = program.getMachine().getMemory().allocate(length);

        for (int i = 0; i < chars.length; i++) {
            program.getMachine().getMemory().setAt(ptr + i, (byte)chars[i]);
        }

        program.getMachine().getStack().push(ptr);
        program.getMachine().getStack().push(length);
    }

    public String[] getStr() {
        return str;
    }
}
