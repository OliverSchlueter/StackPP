package de.oliver.stackpp;

import java.util.*;

public class Lexer {

    private final String content;
    private final String[] lines;
    private final Map<String, String> macros;

    private LinkedList<Instruction> instructions;

    public Lexer(String content) {
        this.content = content.strip();
        this.lines = this.content.split("\r\n");
        this.macros = new HashMap<>();
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

            for (String macroKey : macros.keySet()) {
                String replacement = macros.get(macroKey);

                line = line.replace(macroKey, replacement);
            }

            String[] words = line.strip().split("\\s+");

            if(words.length == 0){
                continue;
            }

            if(words[0].equalsIgnoreCase("#define")){
                if(words.length < 3){
                    continue;
                }

                String replacement = "";

                for (int j = 2; j < words.length; j++) {
                    replacement += words[j] + " ";
                }

                replacement = replacement.substring(0, replacement.length()-1);

                macros.put(words[1], replacement);
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

    public Map<String, String> getMacros() {
        return macros;
    }
}
