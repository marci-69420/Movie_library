package com.example.movie_library;

public class Movie {
    //Atributes
    private String title;
    private int year;
    private String genre;
    private String posterResource;

    //Constructor
    public Movie (String title, int year, String genre, String posterResource) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.posterResource = posterResource;
    }

    //Getters

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getPosterResource() {
        return posterResource;
    }
}
