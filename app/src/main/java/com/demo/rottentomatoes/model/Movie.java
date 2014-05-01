package com.demo.rottentomatoes.model;

import org.parceler.Parcel;

@Parcel
public class Movie {
    public String title;
    public String mpaa_rating;
    public String synopsis;

    public Ratings ratings;
    public Posters posters;
    public Links links;
}
