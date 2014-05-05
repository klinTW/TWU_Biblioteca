package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.HashMap;

public class OptionPrinter {
    private PrintStream printStream;
    private HashMap<String, Command> commandMap;

    public OptionPrinter(PrintStream printStream, HashMap<String, Command> commandMap) {
        this.printStream = printStream;
        this.commandMap = commandMap;
    }


    public void print() {
        Command currentCommand;
        for (Integer i = 0; i < commandMap.keySet().size(); i++) {
            currentCommand = commandMap.get(i.toString());
            printStream.println(currentCommand.returnName());
        }
    }
}
