package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Library {

    private Collection<String> books, checkedOutBooks;
    private PrintStream printStream;
    private StringJoiner joiner;
    private List<Movie> movieList;

    public Library(Collection<String> initialBooks, Collection<String> checkedOutBooks, PrintStream printStream, StringJoiner joiner, List<Movie> movieList) {
        this.books = initialBooks;
        this.checkedOutBooks = checkedOutBooks;
        this.printStream = printStream;
        this.joiner = joiner;
        this.movieList = movieList;
    }

    public void listBooks() {
        String joinedBooks = joiner.join(books);
        printStream.println(joinedBooks);
    }

    public boolean checkout(String book) {
        if (books.contains(book)) {
            books.remove(book);
            checkedOutBooks.add(book);
            printStream.println("Thank you! Enjoy the book.");
            return true;
        } else {
            printStream.println("That book is not available.");
            return false;
        }
    }

    public void returnBook(String book) {
        if (isCheckedOut(book)){
            checkedOutBooks.remove(book);
            books.add(book);
            printStream.println("Thank you for returning the book.");
        } else {
            printStream.println("That is not a valid book to return.");
        }
    }

    public boolean isCheckedOut(String book) {
        return checkedOutBooks.contains(book);
    }

    public void listMovies() {
        for (Movie movie : movieList) {
            ArrayList<String> movieInformation = movie.giveInformation();
            String movieInfoString = "";
            for (int i = 0; i < movieInformation.size(); i++) {
                movieInfoString += movieInformation.get(i);
                if (i != movieInformation.size() - 1) {
                    movieInfoString += ", ";
                }
            }
            printStream.println(movieInfoString);
        }
    }
}
