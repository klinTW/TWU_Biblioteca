package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MovieTest {

    private Movie movie;

    @Before
    public void setUp() {
        movie = new Movie("Movie Name", "Year", "Director", "Rating");
    }

    @Test
    public void shouldReturnListWithNameYearDirectorAndRating() {
        ArrayList<String> correctOutput = new ArrayList<String>();
        correctOutput.add("Movie Name");
        correctOutput.add("Year");
        correctOutput.add("Director");
        correctOutput.add("Rating");

        ArrayList<String> output = movie.giveInformation();
        for (int i = 0; i < output.size(); i++) {
            assertEquals(correctOutput.get(i), output.get(i));
        }
    }

}