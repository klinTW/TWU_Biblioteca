package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = System.out;
        DoneState done = new DoneState(false);
        HashMap<String, Command> commandMap = new HashMap<String, Command>();

        OptionPrinter optionPrinter = new OptionPrinter(out, commandMap);
        Library library = new Library(initialBooks(), new HashSet<String>(), out, new StringJoiner(), initialMovies());

        commandMap.put("1", new ListBookCommand(library));
        commandMap.put("2", new CheckoutCommand(out, library, reader));
        commandMap.put("3", new ReturnCommand(out, reader, library));
        commandMap.put("4", new ListMoviesCommand(library));
        commandMap.put("0", new QuitCommand(done));

        Menu menu = new Menu(out, reader, optionPrinter, commandMap);
        BibliotecaController controller = new BibliotecaController(out, menu, done);

        controller.start();
    }

    private static List<Movie> initialMovies() {
        List<Movie> initialMovies = new ArrayList<Movie>();
        initialMovies.add(new Movie("The Dark Knight", "2009", "Christopher Nolan", "9"));
        initialMovies.add(new Movie("The Amazing Spiderman 2", "2014", "I don't know", "5"));

        return initialMovies;
    }

    private static List<String> initialBooks() {
        List<String> initialBooks = new ArrayList<String>();
        initialBooks.add("The Bible");
        initialBooks.add("Slaughterhouse Five");

        return initialBooks;
    }
}
