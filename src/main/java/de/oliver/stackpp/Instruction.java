package de.oliver.stackpp;

public record Instruction(int line, Token token, String[] args) {
    @Override
    public String toString() {
        return line + ": " + token.name() + " [" + String.join(", ", args) + "]";
    }
}
