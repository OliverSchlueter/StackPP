package de.oliver.stackpp.virtualMachine;

public class Register<T> {

    private final String name;
    private T value;

    public Register(String name, T value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Register{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
