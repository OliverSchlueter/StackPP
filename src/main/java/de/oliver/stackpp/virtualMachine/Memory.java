package de.oliver.stackpp.virtualMachine;

public class Memory {

    private final byte[] memory;

    public Memory(int size) {
        this.memory = new byte[size];
    }

    public byte getAt(int i){
        checkIndexBounds(i);

        return memory[i];
    }

    public void setAt(int i, byte val){
        checkIndexBounds(i);

        memory[i] = val;
    }


    private void checkIndexBounds(int i){
        if(i < 0 || i >= memory.length){
            throw new IndexOutOfBoundsException(i);
        }
    }
}
