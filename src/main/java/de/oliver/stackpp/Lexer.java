package de.oliver.stackpp;

import de.oliver.stackpp.utils.ExceptionHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Lexer {

    private final String content;
    private final String[] lines;
    private final Map<String, String> macros;
    private final List<String> includes;

    private LinkedList<Instruction> instructions;

    public Lexer(String content) {
        this.content = content.strip();
        this.lines = this.content.split("\r\n");
        this.macros = new HashMap<>();
        this.includes = new ArrayList<>();
        this.instructions = new LinkedList<>();
    }

    public LinkedList<Instruction> lex() {

        instructions = new LinkedList<>();

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            if(line.length() == 0){
                continue;
            }

            line = line.strip();

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
            } else if(words[0].equalsIgnoreCase("#include")){
                if(words.length < 2){
                    continue;
                }

                String path = words[1];

                if(includes.contains(path)){
                    continue;
                }

                String content = "";

                try {
                    InputStream in = Lexer.class.getResourceAsStream("/std/" + path);
                    if(in != null) {
                        content = new String(in.readAllBytes());
                    }
                } catch (Exception ignored){ }

                if(content.length() == 0){
                    try {
                        File file = new File(path);
                        if(!file.exists()){
                            continue;
                        }
                        content = Files.readString(Path.of(path));
                    } catch (IOException e) { }
                }

                Lexer lexer = new Lexer(content);
                lexer.getMacros().putAll(macros);
                instructions.addAll(lexer.lex());

                macros.putAll(lexer.getMacros());
                includes.add(path);
                continue;
            }

            Token token = null;

            for (Token t : Token.values()) {
                if(t.getIdentifier().equalsIgnoreCase(words[0])){
                    token = t;
                    break;
                }
            }

            if(token == null){
                ExceptionHelper.throwException(i+1, "Invalid token: '" + words[0] + "'");
                System.exit(1);
                return instructions;
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

    public List<String> getIncludes() {
        return includes;
    }

    public LinkedList<Instruction> getInstructions() {
        return instructions;
    }

    public Map<String, String> getMacros() {
        return macros;
    }
}
