package de.oliver.stackpp;

public enum Token {

    MOVE("move"),
    MOVE_FLOAT("movef"),
    PUSH("push"),
    POP("pop"),
    ADD("add"),
    ADD_FLOAT("addf"),
    SUBTRACT("subtract"),
    MULTIPLY("multiply"),
    DIVIDE("divide"),
    MODULO("modulo"),
    LEFT_SHIFT("lshift"),
    RIGHT_SHIFT("rshift"),
    BITWISE_AND("and"),
    BITWISE_OR("or"),
    BITWISE_XOR("xor"),
    BITWISE_NOT("not"),
    PRINT("print"),
    PRINT_FLOAT("fprint"),
    ASCII_PRINT("aprint"),
    PRINT_STACK("prints"),
    IF("if"),
    ELSE("else"),
    WHILE("while"),
    FUNCTION("function"),
    CALL("call"),
    END("end"),
    MEM_GET("memget"),
    MEM_SET("memset"),
    MEM_ALLOC("malloc"),
    MEM_FREE("free"),
    MEM_DUMP("memdump"),
    SYSCALL("syscall"),
    STRING("str"),
    THROW("throw"),

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
