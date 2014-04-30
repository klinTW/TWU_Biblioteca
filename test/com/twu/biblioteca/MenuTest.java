package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MenuTest {

    private PrintStream printStream;
    private Menu menu;
    private Library library;
    private BufferedReader reader;

    @Before
    public void setUp() throws Exception {
        printStream = mock(PrintStream.class);
        library = mock(Library.class);
        reader = mock(BufferedReader.class);
        menu = new Menu(printStream, library, reader);
    }

    @Test
    public void shouldPrintAnOption(){
        menu.printOptions();
        verify(printStream).println("1) List Books");
    }

    @Test
    public void shouldListBooksWhenSelectedOptionOne() throws IOException {
        menu.runOption("1");
        verify(library).listBooks();
    }

    @Test
    public void shouldRePromptWhenGivenInvalidOption() throws IOException {
        menu.runOption("aasdfasf");
        verify(printStream).println("Select a valid option!");
    }

    @Test
    public void shouldNotListBooksUnlessGiven1() throws IOException {
        menu.runOption("-1");
        verify(library, never()).listBooks();
    }

    @Test
    public void shouldBeTrueWhenQuitReceived() throws IOException {
        menu.runOption("Quit");
        assertThat(menu.isDone(), is(true));
    }

    @Test
    public void shouldBeFalseUnlessQuitReceived() throws IOException {
        menu.runOption("1");
        assertThat(menu.isDone(), is(false));
    }

    @Test
    public void shouldProvideCheckoutOption() {
        menu.printOptions();
        verify(printStream).println("2) Check out book");
    }

    @Test
    public void shouldAskWhichBookWhenCheckingOut() throws IOException {
        menu.runOption("2");
        verify(printStream).println("Which book would you like to check out?");
    }

    @Test
    public void shouldCheckOutSelectedBook() throws IOException {
        when(reader.readLine()).thenReturn("book 1");
        when(library.checkout("book 1")).thenReturn(true);
        menu.runOption("2");
        verify(library).checkout("book 1");
    }

    @Test
    public void shouldInformOfSuccessfulCheckout() throws IOException {
        when(reader.readLine()).thenReturn("book 1");
        when(library.checkout("book 1")).thenReturn(true);
        menu.runOption("2");
        verify(printStream).println("Thank you! Enjoy the book");
    }

    @Test
    public void shouldInformIfInvalidCheckout() throws IOException {
        when(reader.readLine()).thenReturn("WrongBook");
        when(library.checkout("WrongBook")).thenReturn(false);
        menu.runOption("2");
        verify(printStream).println("That book is not available.");
    }

    @Test
    public void shouldHaveReturnBookOption() {
        menu.printOptions();
        verify(printStream).println("3) Return a book");
    }



    @Test
    public void shouldAskForBookToReturn() throws IOException {
        menu.runOption("3");
        verify(reader).readLine();

    }

    @Test
    public void shouldTryToReturnBookToLibrary() throws IOException {
        when(reader.readLine()).thenReturn("A Book");
        menu.runOption("3");
        verify(library).returnBook("A Book");
    }
}