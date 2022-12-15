package de.oliver.stackpp;

import de.oliver.stackpp.virtualMachine.Machine;
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
            content = String.join("\r\n",
                    "",
                    "memset 5 1",
                    "memget 5",
                    "memset 5 2",
                    "memget 5",
                    "prints",
                    ""
                    );
        }

        Lexer lexer = new Lexer(content);
        LinkedList<Instruction> instructions = lexer.lex();

        Parser parser = new Parser(instructions);
        Program program = parser.parse();

        Machine machine = new Machine();

        program.run(machine);
    }
}