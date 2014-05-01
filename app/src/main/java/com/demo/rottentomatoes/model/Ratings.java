package com.demo.rottentomatoes.model;

import org.parceler.Parcel;

@Parcel
public class Ratings {
    public int audience_score;

    public float getRelativeAudienceScore(int maxRange) {
        return ((float) audience_score / 100f) * (float) maxRange;
    }
}
