package com.demo.rottentomatoes;

import android.app.Application;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.demo.rottentomatoes.api.ApiRequestHandler;
import com.demo.rottentomatoes.util.LruImageLoader;

public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();

    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        //noinspection ConstantConditions,PointlessBooleanExpression
        if (BuildConfig.DEBUG) {
            setupDebugConfiguration();
        } else {
            setupReleaseConfiguration();
        }

        requestQueue = Volley.newRequestQueue(this);

        ApiRequestHandler apiRequestHandler = new ApiRequestHandler(requestQueue);
        EventBus.getInstance().register(apiRequestHandler);
    }

    private void setupDebugConfiguration() {
        android.util.Log.i(TAG, "Starting with the debug configuration.");
    }

    private void setupReleaseConfiguration() {
        //Crashlytics.start(this);
    }

    public ImageLoader buildImageLoader(int maxCacheSize) {
        return new LruImageLoader(requestQueue, maxCacheSize);
    }
}
