package de.oliver.stackpp;

public enum Token {

    MOVE("move"),
    PUSH_LITERAL("push"),
    PUSH_REGISTER("pushreg"),
    POP("pop"),
    ADD("add"),
    SUBTRACT("subtract"),
    MULTIPLY("multiply"),
    DIVIDE("divide"),
    MODULO("modulo"),
    PRINT("print"),
    PRINT_STACK("prints"),
    ;

    private final String identifier;

    Token(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
