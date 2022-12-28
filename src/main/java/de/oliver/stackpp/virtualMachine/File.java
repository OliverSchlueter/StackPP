package de.oliver.stackpp.virtualMachine;

public class File {

    private final String name;
    private final String path;
    private int dataPtr;
    private int size;


    public File(String name, String path, int dataPtr, int size) {
        this.name = name;
        this.path = path;
        this.dataPtr = dataPtr;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getDataPtr() {
        return dataPtr;
    }

    public void setDataPtr(int dataPtr) {
        this.dataPtr = dataPtr;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
