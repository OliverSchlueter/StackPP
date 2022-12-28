package de.oliver.stackpp.virtualMachine;

class AllocatedMemory {

    private final int ptr;
    private final int size;

    public AllocatedMemory(int ptr, int size) {
        this.ptr = ptr;
        this.size = size;
    }

    public boolean isInRange(int i) {
        return !(i < ptr || i >= (ptr + size));
    }

    public int getPtr() {
        return ptr;
    }

    public int getSize() {
        return size;
    }
}
