package com.demo.rottentomatoes.ui.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.demo.rottentomatoes.EventBus;
import com.demo.rottentomatoes.MainApplication;
import com.demo.rottentomatoes.R;
import com.demo.rottentomatoes.api.GetBoxOfficeRequest;
import com.demo.rottentomatoes.model.BoxOffice;
import com.demo.rottentomatoes.model.Movie;
import com.demo.rottentomatoes.ui.activities.DetailActivity;
import com.demo.rottentomatoes.util.BindingAdapter;
import com.squareup.otto.Subscribe;

import java.util.List;

public class MainFragment extends ListFragment {

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getInstance().register(this);

        EventBus.getInstance().post(new GetBoxOfficeRequest());
    }

    @Override
    public void onPause() {
        EventBus.getInstance().unregister(this);
        super.onPause();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Movie movie = (Movie) getListView().getItemAtPosition(position);

        // Don't normally like to do fragment to activity navigation in fragments...
        Intent intent = DetailActivity.buildIntent(getActivity(), movie);
        getActivity().startActivity(intent);
    }

    @Subscribe
    public void handleBoxOffice(BoxOffice boxOffice) {
        MoviesAdapter adapter = new MoviesAdapter(getActivity(), boxOffice.movies);
        setListAdapter(adapter);
    }

    private class MoviesAdapter extends BindingAdapter<Movie, ViewHolder> {
        private final List<Movie> movies;

        private MoviesAdapter(Context context, List<Movie> movies) {
            super(context);
            this.movies = movies;
        }

        @Override
        public View newView(LayoutInflater inflater, int position, ViewGroup container) {
            return inflater.inflate(R.layout.movie_item, container, false);
        }

        @Override
        public void bindView(Movie item, int position, View view, ViewHolder viewHolder) {
            String title = item.title;
            String mpaaRating = item.mpaa_rating;
            String thumbnailUrl = item.posters.thumbnail;
            float rating = item.ratings.getRelativeAudienceScore(5);

            viewHolder.title.setText(title);
            viewHolder.mpaaRating.setText(mpaaRating);
            viewHolder.thumbnail.setImageUrl(thumbnailUrl, MainApplication.getImageLoader());
            viewHolder.rating.setRating(rating);
        }

        @Override
        public ViewHolder buildViewHolder(View view) {
            return new ViewHolder(view);
        }

        @Override
        public Movie getItem(int position) {
            return movies.get(position);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public int getCount() {
            return movies.size();
        }
    }

    private static class ViewHolder {
        private NetworkImageView thumbnail;
        private TextView title;
        private TextView mpaaRating;
        private RatingBar rating;

        private ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (NetworkImageView) view.findViewById(R.id.thumbnail);
            mpaaRating = (TextView) view.findViewById(R.id.mpaa_rating);
            rating = (RatingBar) view.findViewById(R.id.rating);
        }
    }
}
