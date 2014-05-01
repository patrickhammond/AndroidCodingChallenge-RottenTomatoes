package com.demo.rottentomatoes.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.demo.rottentomatoes.EventBus;
import com.demo.rottentomatoes.MainApplication;
import com.demo.rottentomatoes.R;
import com.demo.rottentomatoes.model.Movie;
import com.squareup.otto.Subscribe;

public class DetailFragment extends Fragment {
    private NetworkImageView imageView;
    private TextView titleView;
    private TextView mpaaRatingView;
    private RatingBar ratingView;
    private TextView descriptionView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        titleView = (TextView) view.findViewById(R.id.title);
        imageView = (NetworkImageView) view.findViewById(R.id.image);
        mpaaRatingView = (TextView) view.findViewById(R.id.mpaa_rating);
        ratingView = (RatingBar) view.findViewById(R.id.rating);
        descriptionView = (TextView) view.findViewById(R.id.description);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // For our current purposes we really only care about activity scoped events should be on a different bus
        EventBus.getInstance().register(this);
    }

    @Override
    public void onPause() {
        // For our current purposes we really only care about activity scoped events should be on a different bus
        EventBus.getInstance().unregister(this);

        super.onPause();
    }

    @Subscribe
    public void handleMovie(Movie movie) {
        String title = movie.title;
        String mpaaRating = movie.mpaa_rating;
        String imageUrl = movie.posters.profile;
        int stars = ratingView.getNumStars();
        float rating = movie.ratings.getRelativeAudienceScore(stars);
        String description = movie.synopsis;

        titleView.setText(title);
        mpaaRatingView.setText(mpaaRating);
        imageView.setImageUrl(imageUrl, MainApplication.getImageLoader());
        ratingView.setRating(rating);
        descriptionView.setText(description);
    }
}
