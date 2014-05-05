package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LibraryTest {

    private Collection<String> books;
    private Library library;
    private PrintStream printStream;
    private StringJoiner joiner;
    private Collection<String> checkedOutBooks;
    private Movie movie;
    private ArrayList<Movie> movieList;

    @Before
    public void setUp() throws Exception {
        books = new HashSet<String>();
        checkedOutBooks = new HashSet<String>();
        movie = mock(Movie.class);
        movieList = new ArrayList<Movie>();
        printStream = mock(PrintStream.class);
        joiner = mock(StringJoiner.class);
        library = new Library(books, checkedOutBooks, printStream, joiner, movieList);
    }


    @Test
    public void shouldJoinBookList() {
        library.listBooks();
        verify(joiner).join(books);
    }

    @Test
    public void shouldPrintJoinedBooks() {
        String joinedBooks = "aaa";
        when(joiner.join(books)).thenReturn(joinedBooks);
        library.listBooks();
        verify(printStream).println(joinedBooks);
    }

    @Test
    public void shouldNotPrintCheckedOutBook() {
        String book1 = "aaa";
        books.add(book1);
        library.checkout(book1);
        assertThat(books, not(hasItem(book1)));
    }

    @Test
    public void shouldReturnBook(){
        checkedOutBooks.add("Book 3");
        library = new Library(books, checkedOutBooks, printStream, joiner, movieList);
        library.returnBook("Book 3");
        assertTrue(books.contains("Book 3"));
    }

    @Test
    public void shouldPrintSuccessfulCheckoutMessage() {
        books.add("A Good Book");
        library = new Library(books, checkedOutBooks, printStream, joiner, movieList);
        library.checkout("A Good Book");
        verify(printStream).println("Thank you! Enjoy the book.");
    }

    @Test
    public void shouldPrintUnsuccessfulCheckoutMessage() {
        library.checkout("I'm not in the library");
        verify(printStream).println("That book is not available.");
    }

    @Test
    public void shouldPrintSuccessfulReturnMessage() {
        checkedOutBooks.add("Boo!");
        library = new Library(books, checkedOutBooks, printStream, joiner, movieList);
        library.returnBook("Boo!");
        verify(printStream).println("Thank you for returning the book.");
    }

    @Test
    public void shouldReturnTrueIfBookIsCheckedOut(){
        books.add("boo2");
        library = new Library(books, checkedOutBooks, printStream, joiner, movieList);
        library.checkout("boo2");
        assertTrue(library.isCheckedOut("boo2"));
    }

    @Test
    public void shouldRemoveBookFromCheckoutListWhenReturned() {
        checkedOutBooks.add("A Book About Books");
        library = new Library(books, checkedOutBooks, printStream, joiner, movieList);
        library.returnBook("A Book About Books");
        assertFalse(library.isCheckedOut("A Book About Books"));
    }

    @Test
    public void shouldPrintUnsuccessfulReturnMessage() {
        library.returnBook("No Way!");
        verify(printStream).println("That is not a valid book to return.");
    }

    @Test
    public void shouldPrintListOfMovies() {
        ArrayList<String> movieInfo = new ArrayList<String>();
        movieInfo.add("A Movie");
        movieInfo.add("A Year");
        movieInfo.add("A Director");
        movieInfo.add("A Rating");
        when(movie.giveInformation()).thenReturn(movieInfo);
        library.listMovies();
        verify(printStream).println("A Movie, A Year, A Director, A Rating");
    }

    @Test
    public void shouldPrintMultipleMovies(){
        Movie movie2 = mock(Movie.class);
        movieList.add(movie);
        movieList.add(movie2);
        library = new Library(books, checkedOutBooks, printStream, joiner, movieList);
        ArrayList<String> movieInfo = new ArrayList<String>();
        movieInfo.add("A Movie");
        movieInfo.add("A Year");
        movieInfo.add("A Director");
        movieInfo.add("A Rating");
        ArrayList<String> movieInfo2 = new ArrayList<String>();
        movieInfo2.add("A Second");
        movieInfo2.add("A Year");
        movieInfo2.add("Kevin");
        movieInfo2.add("44444");
        when(movie.giveInformation()).thenReturn(movieInfo);
        when(movie2.giveInformation()).thenReturn(movieInfo2);
        library.listMovies();
        verify(printStream).println("A Movie, A Year, A Director, A Rating");
        verify(printStream).println("A Second, A Year, Kevin, 44444");

    }


}

