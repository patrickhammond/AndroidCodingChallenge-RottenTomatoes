package com.demo.rottentomatoes.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

public class LruImageLoader extends ImageLoader {

    private static class LruImageCache implements ImageCache {
        private final LruCache<String, Bitmap> cache;

        private LruImageCache(int maxCacheSize) {
            cache = new LruCache<String, Bitmap>(maxCacheSize);
        }

        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url, bitmap);
        }
        public Bitmap getBitmap(String url) {
            return cache.get(url);
        }
    }

    public LruImageLoader(RequestQueue queue, int maxCacheSize) {
        super(queue, new LruImageCache(maxCacheSize));
    }
}
