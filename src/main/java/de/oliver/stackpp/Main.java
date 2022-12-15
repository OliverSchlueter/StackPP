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
                    function max\r
                        if a > b\r
                            push a\r
                            print a\r
                        else\r
                            push b\r
                            print b\r
                        end\r
                    end\r
                                        
                    move 21 a\r
                    move 4 b\r
                    call max\r
                                        
                    pop c\r
                    print c\r
                    """;
        }

        Lexer lexer = new Lexer(content);
        LinkedList<Instruction> instructions = lexer.lex();

        Parser parser = new Parser(instructions);
        Program program = parser.parse();

        program.run();
    }
}