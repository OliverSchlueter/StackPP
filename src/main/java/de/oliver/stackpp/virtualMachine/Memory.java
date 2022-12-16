package de.oliver.stackpp.virtualMachine;

import java.util.ArrayList;
import java.util.List;

public class Memory {

    private final byte[] memory;
    private final List<AllocatedMemory> allocatedMemory;

    public Memory(int size) {
        this.memory = new byte[size];
        this.allocatedMemory = new ArrayList<>();
    }
    
    public int allocate(int size){
        for (int i = 0; i < memory.length-size; i++) {
            boolean isFree = true;
            l2:
            for (int j = 0; j < size; j++) {
                for (AllocatedMemory am : allocatedMemory) {
                    if(am.isInRange(i + j)){
                        isFree = false;
                        break l2;
                    }
                }
            }

            if(isFree){
                allocatedMemory.add(new AllocatedMemory(i, size));
                return i;
            }
        }

        return -1;
    }

    public void free(int ptr){
        allocatedMemory.removeIf(am -> am.ptr() == ptr);
    }

    public byte getAt(int i){
        checkIndexBounds(i);

        return memory[i];
    }

    public void setAt(int i, byte val){
        checkIndexBounds(i);

        memory[i] = val;
    }
    
    public void dump(){
        for (int i = 0; i < memory.length; i++) {
            System.out.print(memory[i]);
        }

        System.out.println();
    }

    private void checkIndexBounds(int i){
        if(i < 0 || i >= memory.length){
            throw new IndexOutOfBoundsException(i);
        }
    }
}

record AllocatedMemory(int ptr, int size) {

    public boolean isInRange(int i){
        return !(i < ptr || i >= (ptr + size));
    }

}
