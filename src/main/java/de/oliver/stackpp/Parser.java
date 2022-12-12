package de.oliver.stackpp;

import de.oliver.stackpp.operations.*;

public class Parser {

    private final String content;
    private final String[] words;

    private int currentWordIndex;
    private Program program;

    public Parser(String content) {
        this.content = content;
        this.words = this.content.split(" ");
        this.currentWordIndex = 0;
    }

    public Program parse(){
        program = new Program();

        for (int i = 0; i < words.length; i++) {
            parseNextWord();
        }

        return program;
    }

    private void parseNextWord(){
        String currentWord = words[currentWordIndex];

        if(currentWord.length() == 0 || currentWord.equals(" ")){
            currentWordIndex++;
            return;
        }

        Operation operation = null;

        if (Program.supportedOperations.length != 7) {
            System.err.println("At least one operation is not implemented");
            System.exit(1);
            return;
        }

        switch (currentWord.toLowerCase()){
            case "+" -> operation = new AddOperation(program);
            case "-" -> operation = new SubtractOperation(program);
            case "*" -> operation = new MultiplyOperation(program);
            case "/" -> operation = new DivideOperation(program);
            case "%" -> operation = new ModuloOperation(program);
            case "." -> operation = new PopPrintOperation(program);
            case "," -> operation = new PrintOperation(program);
            default -> operation = new PushOperation(program, Integer.parseInt(currentWord));
        }

        program.getInstructions().offer(operation);

        currentWordIndex++;
    }

    public String getContent() {
        return content;
    }

    public String[] getWords() {
        return words;
    }

    public int getCurrentWordIndex() {
        return currentWordIndex;
    }

    public Program getProgram() {
        return program;
    }
}
