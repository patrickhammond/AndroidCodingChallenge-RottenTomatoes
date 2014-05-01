package com.demo.rottentomatoes.api;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.demo.rottentomatoes.EventBus;
import com.demo.rottentomatoes.event.LoadBoxOffice;
import com.demo.rottentomatoes.model.BoxOffice;
import com.squareup.otto.Subscribe;

public class ApiRequestHandler {
    private final RequestQueue requestQueue;

    public ApiRequestHandler(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Subscribe
    public void handleLoadBoxOfficeRequest(LoadBoxOffice event) {
        requestQueue.add(new GetBoxOfficeRequest(new Listener<BoxOffice>() {
            @Override
            public void onResponse(BoxOffice boxOffice) {
                EventBus.getInstance().post(boxOffice);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                EventBus.getInstance().post(volleyError);
            }
        }));
    }
}
