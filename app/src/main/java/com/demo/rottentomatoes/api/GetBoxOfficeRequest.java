package com.demo.rottentomatoes.api;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.demo.rottentomatoes.model.BoxOffice;

public class GetBoxOfficeRequest extends GsonRequest<BoxOffice> {
    private static final String URL = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=2u9yscz5d4jd5m8edfb2yh89";

    public GetBoxOfficeRequest(Listener<BoxOffice> listener, ErrorListener errorListener) {
        super(URL, BoxOffice.class, null, listener, errorListener);
    }
}
