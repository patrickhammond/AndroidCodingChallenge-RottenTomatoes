package com.demo.rottentomatoes.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.demo.rottentomatoes.EventBus;
import com.demo.rottentomatoes.R;
import com.demo.rottentomatoes.model.Movie;
import com.squareup.otto.Subscribe;

public class MainActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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

    @Subscribe
    public void handleVolleyError(VolleyError volleyError) {
        Toast.makeText(this, "Oops: " + volleyError, Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void handleMovie(Movie movie) {
        Intent intent = DetailActivity.buildIntent(this, movie);
        startActivity(intent);
    }
}
