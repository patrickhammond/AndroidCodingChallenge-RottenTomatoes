package com.demo.rottentomatoes.model;

import org.parceler.Parcel;

@Parcel
public class Links {
    public String alternate;

    public String getTrailerUrl() {
        return alternate + "trailer";
    }
}
