package com.demo.rottentomatoes.model;

import java.io.Serializable;

public class Links implements Serializable {
    public String alternate;

    public String getTrailerUrl() {
        return alternate + "trailer";
    }
}
