package com.yozio.android.helpers;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.LruCache;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class ImageCache {
    private static final String TAG = "ImageCache";
    private LruCache<String, Bitmap> mMemoryCache;

    private ImageCache(float f) {
        init(f);
    }

    public static ImageCache getInstance(FragmentManager fragmentManager, float f) {
        RetainFragment findOrCreateRetainFragment = findOrCreateRetainFragment(fragmentManager);
        ImageCache imageCache = (ImageCache) findOrCreateRetainFragment.getObject();
        if (imageCache == null) {
            ImageCache imageCache2 = new ImageCache(f);
            findOrCreateRetainFragment.setObject(imageCache2);
            return imageCache2;
        }
        return imageCache;
    }

    private void init(float f) {
        this.mMemoryCache = new LruCache<String, Bitmap>(calculateMemCacheSize(f)) { // from class: com.yozio.android.helpers.ImageCache.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.support.v4.util.LruCache
            public int sizeOf(String str, Bitmap bitmap) {
                int bitmapSize = ImageCache.getBitmapSize(bitmap) / 1024;
                if (bitmapSize == 0) {
                    return 1;
                }
                return bitmapSize;
            }
        };
    }

    public void addBitmapToCache(String str, Bitmap bitmap) {
        if (str != null && bitmap != null && this.mMemoryCache != null && this.mMemoryCache.get(str) == null) {
            this.mMemoryCache.put(str, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String str) {
        Bitmap bitmap;
        if (this.mMemoryCache == null || (bitmap = this.mMemoryCache.get(str)) == null) {
            return null;
        }
        return bitmap;
    }

    @TargetApi(DateTimeConstants.DECEMBER)
    public static int getBitmapSize(Bitmap bitmap) {
        return Utils.hasHoneycombMR1() ? bitmap.getByteCount() : bitmap.getRowBytes() * bitmap.getHeight();
    }

    public static int calculateMemCacheSize(float f) {
        if (f < 0.05f || f > 0.8f) {
            throw new IllegalArgumentException("setMemCacheSizePercent - percent must be between 0.05 and 0.8 (inclusive)");
        }
        return Math.round((((float) Runtime.getRuntime().maxMemory()) * f) / 1024.0f);
    }

    public static RetainFragment findOrCreateRetainFragment(FragmentManager fragmentManager) {
        RetainFragment retainFragment = (RetainFragment) fragmentManager.findFragmentByTag(TAG);
        if (retainFragment == null) {
            RetainFragment retainFragment2 = new RetainFragment();
            fragmentManager.beginTransaction().add(retainFragment2, TAG).commitAllowingStateLoss();
            return retainFragment2;
        }
        return retainFragment;
    }

    /* loaded from: classes.dex */
    public class RetainFragment extends Fragment {
        private Object mObject;

        @Override // android.support.v4.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setRetainInstance(true);
        }

        public void setObject(Object obj) {
            this.mObject = obj;
        }

        public Object getObject() {
            return this.mObject;
        }
    }
}
