package com.demo.rottentomatoes.model;

import java.io.Serializable;

public class Ratings implements Serializable {
    public int audience_score;

    public float getRelativeAudienceScore(int maxRange) {
        return ((float) audience_score / 100f) * (float) maxRange;
    }
}
