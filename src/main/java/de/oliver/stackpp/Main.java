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
                    "move 5 a",
                    "print a",
                    "print a",
                    "exit a",
                    "print a",
                    ""
                    );
        }

        Lexer lexer = new Lexer(content);
        LinkedList<Instruction> instructions = lexer.lex();

        Parser parser = new Parser(instructions);
        Program program = parser.parse();

        Machine machine = new Machine();
        machine.runProgram(program);
    }
}