package com.demo.rottentomatoes.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.demo.rottentomatoes.EventBus;
import com.demo.rottentomatoes.R;
import com.demo.rottentomatoes.model.Movie;
import com.squareup.otto.Produce;
import org.parceler.Parcels;

public class DetailActivity extends Activity {
    private static final String EXTRA_MOVIE = "movie";

    public static Intent buildIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, Parcels.wrap(movie));
        return intent;
    }

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movie = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_MOVIE));
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        EventBus.getInstance().unregister(this);
        super.onPause();
    }

    /**
     * This really should be producing on an activity specific bus vs the application bus because it is only
     * designed to provide the activity value to the fragments.
     */
    @Produce
    public Movie produceMovie() {
        return movie;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.play_trailer) {
            playTrailer();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void playTrailer() {
        String url = movie.links.getTrailerUrl();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
