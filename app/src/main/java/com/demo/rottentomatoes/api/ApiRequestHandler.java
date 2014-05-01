package com.demo.rottentomatoes.api;

import android.util.Log;
import com.android.volley.RequestQueue;
import com.squareup.otto.Subscribe;

public class ApiRequestHandler {
    private final RequestQueue requestQueue;

    public ApiRequestHandler(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Subscribe
    public void handleGetBoxOfficeRequest(GetBoxOfficeRequest request) {
        Log.v("DEBUG", "handleGetBoxOfficeRequest");
        requestQueue.add(request);
    }
}
