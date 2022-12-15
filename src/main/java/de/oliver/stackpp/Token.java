package de.oliver.stackpp;

public enum Token {

    MOVE("move"),
    PUSH("push"),
    POP("pop"),
    ADD("add"),
    SUBTRACT("subtract"),
    MULTIPLY("multiply"),
    DIVIDE("divide"),
    MODULO("modulo"),
    PRINT("print"),
    PRINT_STACK("prints"),
    IF("if"),
    ELSE("else"),
    WHILE("while"),
    FUNCTION("function"),
    CALL("call"),
    END("end"),
    MEM_GET("memget"),
    MEM_SET("memset"),
    EXIT("exit"),

    EQUAL_SIGN("="),
    GREATER_SIGN(">"),
    LOWER_SIGN("<"),
    ;

    private final String identifier;

    Token(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static Token getTokenByIdentifier(String i){
        for (Token token : values()) {
            if(token.getIdentifier().equalsIgnoreCase(i)){
                return token;
            }
        }

        return null;
    }
}
