package com.twu.biblioteca;

public class ListMoviesCommand implements Command {
    private Library library;

    public ListMoviesCommand(Library library) {
        this.library = library;
    }

    @Override
    public void execute() {
        library.listMovies();
    }

    @Override
    public String returnName() {
        return "4) List movies";
    }
}
