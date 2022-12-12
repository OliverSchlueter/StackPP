package de.oliver.stackpp;

import de.oliver.stackpp.virtualMachine.Program;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {

        String content = "";

        if(args.length == 1){
            content = Files.readString(Path.of(args[0]));
        } else {
            content = """
                    push    3       ; push '3' onto the stack\r
                    push    6       ; push '6' onto the stack\r
                    push    2       ; push '2' onto the stack\r
                                        
                    pop     a       ; puts the top element of the stack into the 'a' register\r
                    pop     b       ; puts the top element of the stack into the 'b' register\r
                                        
                    add     a b     ; adds the values in the 'a' and 'b' registers\r
                                        
                    pop     b       ; puts the top element of the stack into the 'b' register\r
                                        
                    add     a b     ; adds the values in the 'a' and 'b' registers\r
                                        
                    pushreg a       ; push the value of the 'a' register onto the stack\r
                                        
                    prints
                    """;
        }

        Lexer lexer = new Lexer(content);
        LinkedList<Instruction> instructions = lexer.lex();

        Parser parser = new Parser(instructions);
        Program program = parser.parse();

        program.run();
    }
}