package de.oliver.stackpp.virtualMachine;

import org.apache.commons.lang3.CharUtils;

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
        allocatedMemory.removeIf(am -> am.getPtr() == ptr);
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
        // print header
        System.out.println("           0  1  2  3  4  5  6  7  8  9  A  B  C  D  E  F");

        for (int row = 0; row < memory.length/16; row++) {

            // if row is only filled with '0x00' then skip
            boolean isOnlyZeros = true;
            for (int col = 0; col < 16; col++) {
                if(memory[row*16+col] != 0){
                    isOnlyZeros = false;
                    break;
                }
            }

            if(isOnlyZeros){
                continue;
            }

            // print row location
            System.out.printf("%07x0  ", row);

            // print values in row
            for (int col = 0; col < 16; col++) {
                System.out.printf("%02x ", memory[row*16+col]);
            }

            // print as ascii char
            System.out.print(" |");
            for (int col = 0; col < 16; col++) {
                char ascii = (char)memory[row*16+col];

                boolean printable = CharUtils.isAsciiPrintable(ascii);
                System.out.print(printable ? ascii : ".");
            }
            System.out.print("|");

            // print new line
            System.out.println();
        }
    }

    private void checkIndexBounds(int i){
        if(i < 0 || i >= memory.length){
            throw new IndexOutOfBoundsException(i);
        }
    }
}