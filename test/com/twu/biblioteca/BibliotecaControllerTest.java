package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class BibliotecaControllerTest {

    private PrintStream out;
    private BibliotecaController controller;
    private Library library;
    private Menu menu;

    @Before
    public void setUp() throws Exception {
        out = mock(PrintStream.class);
        library = mock(Library.class);
        menu = mock(Menu.class);
        controller = new BibliotecaController(out, library, menu);
    }

    @Test
    public void shouldPrintWelcomeMessageWhenStarts() throws IOException {
        controller.start();
        verify(out).println("Welcome!");
    }

    @Test
    public void shouldDisplayMenuWhenStarts() throws IOException {
        controller.start();
        verify(menu).printOptions();
    }

    @Test
    public void shouldPerformChosenOptionWhenStarts() throws IOException {
        controller.start();
        verify(menu).runOption(anyString());
    }

    @Test
    public void shouldStopWhenQuitIsSelected() throws IOException {
        when(menu.isDone()).thenReturn(true);
        when(menu.getInput()).thenReturn("Quit");
        controller.start();
        verify(menu,times(1)).runOption("Quit");
    }

    @Test
    public void shouldPromptAgainIfNotQuiting() throws IOException {
        when(menu.isDone()).thenReturn(false).thenReturn(true);
        when(menu.getInput()).thenReturn("1").thenReturn("Quit");
        controller.start();
        verify(menu, times(2)).runOption(anyString());
    }
}