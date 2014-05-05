package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class MenuTest {

    private PrintStream out;
    private Menu menu;
    private Library library;
    private BufferedReader reader;
    private OptionPrinter optionPrinter;
    private HashMap<String, Command> commandMap;
    private DoneState done;

    @Before
    public void setUp() throws Exception {
        out = mock(PrintStream.class);
        library = mock(Library.class);
        reader = mock(BufferedReader.class);
        optionPrinter = mock(OptionPrinter.class);
        done = mock(DoneState.class);

        commandMap = new HashMap<String, Command>();
        commandMap.put("1", new ListBookCommand(library));
        commandMap.put("2", new CheckoutCommand(out, library, reader));
        commandMap.put("3", new ReturnCommand(out, reader, library));
        commandMap.put("4", new ListMoviesCommand(library));
        commandMap.put("0", new QuitCommand(done));

        menu = new Menu(out, reader, optionPrinter, commandMap);
    }

    @Test
    public void shouldPrintOptionsWhenRun() throws IOException {
        when(reader.readLine()).thenReturn("1");
        menu.run();
        verify(optionPrinter).print();
    }
    
    @Test
    public void shouldDisplayBooksWhenGivenOptionOne() throws IOException {
        when(reader.readLine()).thenReturn("1");
        menu.run();
        verify(library).listBooks();     
    }
    
    @Test
    public void shouldDisplayErrorMessageWhenGivenInvalidInput() throws IOException {
        when(reader.readLine()).thenReturn("Invalid Input");
        menu.run();
        verify(out).println("Select a valid option!");
    }

    @Test
    public void shouldAttemptToCheckoutBookWhenUserRequests() throws IOException {
        when(reader.readLine()).thenReturn("2").thenReturn("A Book");
        menu.run();
        verify(library).checkout("A Book");
    }

    @Test
    public void shouldPromptUserToEnterABookNameWhenCheckingOut() throws IOException {
        when(reader.readLine()).thenReturn("2");
        menu.run();
        verify(out).println("Which book would you like to check out?");
    }

    @Test
    public void shouldPromptUserToEnterABookNameWhenReturning() throws IOException {
        when(reader.readLine()).thenReturn("3");
        menu.run();
        verify(out).println("Which book would you like to return?");
    }

    @Test
    public void shouldAcceptUserInputWhenReturning() throws IOException {
        when(reader.readLine()).thenReturn("3");
        InOrder inOrder = inOrder(out, reader);
        menu.run();
        inOrder.verify(out).println("Which book would you like to return?");
        inOrder.verify(reader).readLine();
    }

    @Test
    public void shouldAttemptToReturnBookWhenUserRequest() throws IOException {
        when(reader.readLine()).thenReturn("3").thenReturn("book");
        menu.run();
        verify(library).returnBook("book");
    }

    @Test
    public void shouldAttemptToListMovies() throws IOException {
        when(reader.readLine()).thenReturn("4");
        menu.run();
        verify(library).listMovies();
    }

}