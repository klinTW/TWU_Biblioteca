package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CheckoutCommand implements Command {

    private PrintStream printer;
    private Library library;
    private BufferedReader reader;

    public CheckoutCommand(PrintStream printer, Library library, BufferedReader reader){
        this.printer = printer;
        this.library = library;
        this.reader = reader;
    }

    @Override
    public void execute() {
        printer.println("Which book would you like to check out?");
        try {
            library.checkout(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String returnName() {
        return "2) Check out book";
    }
}
