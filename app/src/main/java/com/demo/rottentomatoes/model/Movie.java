package com.demo.rottentomatoes.model;

import java.io.Serializable;

public class Movie implements Serializable {
    public String title;
    public String mpaa_rating;
    public String synopsis;

    public Ratings ratings;
    public Posters posters;
    public Links links;
}
