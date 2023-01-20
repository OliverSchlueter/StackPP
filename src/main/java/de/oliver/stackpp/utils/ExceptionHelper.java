package de.oliver.stackpp.utils;

public class ExceptionHelper {

    public static void throwException(int line, String description){
        System.err.println("[@" + line + "] " + description);
    }

}
