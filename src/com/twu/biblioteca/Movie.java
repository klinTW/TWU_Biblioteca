package com.twu.biblioteca;

import java.util.ArrayList;

public class Movie {
    private String name;
    private String year;
    private String director;
    private String rating;

    public Movie(String name, String year, String director, String rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public ArrayList<String> giveInformation() {
        ArrayList<String> returnValues = new ArrayList<String>();
        returnValues.add(name);
        returnValues.add(year);
        returnValues.add(director);
        returnValues.add(rating);
        return returnValues;
    }
}
