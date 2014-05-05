package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class OptionPrinterTest {

    private PrintStream printStream = mock(PrintStream.class);
    private HashMap<String, Command> commandMap = new HashMap<String, Command>();
    private Library library = mock(Library.class);
    private OptionPrinter optionPrinter = new OptionPrinter(printStream, commandMap);
    private BufferedReader reader = mock(BufferedReader.class);
    private DoneState done = mock(DoneState.class);

    @Before
    public void setUp() {
        commandMap.put("1", new ListBookCommand(library));
        commandMap.put("2", new CheckoutCommand(printStream, library, reader));
        commandMap.put("3", new ReturnCommand(printStream,  reader, library));
        commandMap.put("4", new ListMoviesCommand(library));
        commandMap.put("0", new QuitCommand(done));
    }

    @Test
    public void shouldPrintOptions() {
        optionPrinter.print();
        verify(printStream).println("0) Quit");
        verify(printStream).println("1) List books");
        verify(printStream).println("2) Check out book");
        verify(printStream).println("3) Return book");
        verify(printStream).println("4) List movies");

    }



}