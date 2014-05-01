package com.demo.rottentomatoes;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;
import com.demo.rottentomatoes.api.ApiRequestHandler;

public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();

    private static RequestQueue requestQueue;

    private static ImageLoader imageLoader;

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

        // In memory image cache that has the life of a process is very bad!!!
        imageLoader = new ImageLoader(MainApplication.getRequestQueue(), new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
    }

    private void setupDebugConfiguration() {
        android.util.Log.i(TAG, "Starting with the debug configuration.");
    }

    private void setupReleaseConfiguration() {
        //Crashlytics.start(this);
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static ImageLoader getImageLoader() {
        return imageLoader;
    }
}
