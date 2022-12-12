package de.oliver.stackpp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        String content = "";

        if(args.length == 1){
            content = Files.readString(Path.of(args[0]));
        } else {
            content = "1 , 2 ,";
        }

        Parser parser = new Parser(content);
        Program program = parser.parse();

        program.run();
    }
}