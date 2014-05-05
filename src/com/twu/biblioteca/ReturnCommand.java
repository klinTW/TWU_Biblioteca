package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class ReturnCommand implements Command {
    private PrintStream printer;
    private BufferedReader reader;
    private Library library;

    public ReturnCommand(PrintStream printer, BufferedReader reader, Library library) {

        this.printer = printer;
        this.reader = reader;
        this.library = library;
    }

    @Override
    public void execute() {
        printer.println("Which book would you like to return?");
        try {
            library.returnBook(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String returnName() {
        return "3) Return book";
    }
}
