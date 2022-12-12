package de.oliver.stackpp;

import java.util.*;

public class Lexer {

    private final String content;
    private final String[] lines;

    private LinkedList<Instruction> instructions;

    public Lexer(String content) {
        this.content = content.strip();
        this.lines = this.content.split("\r\n");
        this.instructions = new LinkedList<>();
    }

    public LinkedList<Instruction> lex(){

        instructions = new LinkedList<>();

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            if(line.length() == 0){
                continue;
            }

            // skip comment lines
            if(line.startsWith(";")){
                continue;
            }

            // remove comments
            if(line.contains(";")){
                line = line.substring(0, line.indexOf(";"));
            }

            String[] words = line.strip().split("\\s+");

            if(words.length == 0){
                continue;
            }

            Token token = null;

            for (Token t : Token.values()) {
                if(t.getIdentifier().equalsIgnoreCase(words[0])){
                    token = t;
                    break;
                }
            }

            String[] args = new String[words.length-1];

            for (int j = 1; j < words.length; j++) {
                String w = words[j];
                args[j-1] = w;
            }

            Instruction instruction = new Instruction(i+1, token, args);
            instructions.add(instruction);
        }

        return instructions;
    }

    public String getContent() {
        return content;
    }

    public String[] getLines() {
        return lines;
    }

    public LinkedList<Instruction> getInstructions() {
        return instructions;
    }
}
