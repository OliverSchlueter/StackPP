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
                    move 5 a\r
                    move 5 b\r
                    add a 5\r
                    move 2 b\r
                    subtract a b\r
                    print a
                    """;
        }

        Lexer lexer = new Lexer(content);
        LinkedList<Instruction> instructions = lexer.lex();

        Parser parser = new Parser(instructions);
        Program program = parser.parse();

        program.run();
    }
}