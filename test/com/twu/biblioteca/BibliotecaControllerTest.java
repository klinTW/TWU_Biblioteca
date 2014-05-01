package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class BibliotecaControllerTest {

    private PrintStream out;
    private BibliotecaController controller;
    private Menu menu;

    @Before
    public void setUp() throws Exception {
        out = mock(PrintStream.class);
        menu = mock(Menu.class);
        controller = new BibliotecaController(out, menu);
    }

    @Test
    public void shouldPrintWelcomeMessageOnStart() throws IOException {
        when(menu.isDone()).thenReturn(true);
        controller.start();
        verify(out).println("Welcome!");
    }

    @Test
    public void shouldLetUserChooseOptions() throws IOException {
        when(menu.isDone()).thenReturn(true);
        controller.start();
        verify(menu).run();
    }

    @Test
    public void shouldQuitWhenOptionSelected() throws IOException {
        when(menu.isDone()).thenReturn(false).thenReturn(true);
        controller.start();
        verify(menu, times(2)).run();
    }

}