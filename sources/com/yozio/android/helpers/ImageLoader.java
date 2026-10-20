package com.yozio.android.helpers;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import java.io.FileDescriptor;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public abstract class ImageLoader {
    private static final int FADE_IN_TIME = 200;
    private static final String TAG = "ImageLoader";
    private ImageCache mImageCache;
    private int mImageSize;
    private Bitmap mLoadingBitmap;
    private Resources mResources;
    private boolean mFadeInBitmap = true;
    private boolean mPauseWork = false;
    private final Object mPauseWorkLock = new Object();

    public abstract Bitmap processBitmap(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public ImageLoader(Context context, int i) {
        this.mResources = context.getResources();
        this.mImageSize = i;
    }

    public int getImageSize() {
        return this.mImageSize;
    }

    public void loadImage(Object obj, ImageView imageView) {
        if (obj == null) {
            imageView.setImageBitmap(this.mLoadingBitmap);
            return;
        }
        Bitmap bitmap = null;
        if (this.mImageCache != null) {
            bitmap = this.mImageCache.getBitmapFromMemCache(String.valueOf(obj));
        }
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else if (cancelPotentialWork(obj, imageView)) {
            BitmapWorkerTask bitmapWorkerTask = new BitmapWorkerTask(imageView);
            imageView.setImageDrawable(new AsyncDrawable(this.mResources, this.mLoadingBitmap, bitmapWorkerTask));
            bitmapWorkerTask.execute(obj);
        }
    }

    public void setLoadingImage(int i) {
        this.mLoadingBitmap = BitmapFactory.decodeResource(this.mResources, i);
    }

    public void addImageCache(FragmentManager fragmentManager, float f) {
        this.mImageCache = ImageCache.getInstance(fragmentManager, f);
    }

    public void setImageFadeIn(boolean z) {
        this.mFadeInBitmap = z;
    }

    public static void cancelWork(ImageView imageView) {
        BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
        if (bitmapWorkerTask != null) {
            bitmapWorkerTask.cancel(true);
        }
    }

    public static boolean cancelPotentialWork(Object obj, ImageView imageView) {
        BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
        if (bitmapWorkerTask == null) {
            return true;
        }
        Object obj2 = bitmapWorkerTask.data;
        if (obj2 == null || !obj2.equals(obj)) {
            bitmapWorkerTask.cancel(true);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                return ((AsyncDrawable) drawable).getBitmapWorkerTask();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class BitmapWorkerTask extends AsyncTask<Object, Void, Bitmap> {
        private Object data;
        private final WeakReference<ImageView> imageViewReference;

        public BitmapWorkerTask(ImageView imageView) {
            this.imageViewReference = new WeakReference<>(imageView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(Object... objArr) {
            this.data = objArr[0];
            String valueOf = String.valueOf(this.data);
            Bitmap bitmap = null;
            synchronized (ImageLoader.this.mPauseWorkLock) {
                while (ImageLoader.this.mPauseWork && !isCancelled()) {
                    try {
                        ImageLoader.this.mPauseWorkLock.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
            if (!isCancelled() && getAttachedImageView() != null) {
                bitmap = ImageLoader.this.processBitmap(objArr[0]);
            }
            if (bitmap != null && ImageLoader.this.mImageCache != null) {
                ImageLoader.this.mImageCache.addBitmapToCache(valueOf, bitmap);
            }
            return bitmap;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }
            ImageView attachedImageView = getAttachedImageView();
            if (bitmap == null || attachedImageView == null) {
                return;
            }
            ImageLoader.this.setImageBitmap(attachedImageView, bitmap);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        @TargetApi(11)
        public void onCancelled(Bitmap bitmap) {
            super.onCancelled((BitmapWorkerTask) bitmap);
            synchronized (ImageLoader.this.mPauseWorkLock) {
                ImageLoader.this.mPauseWorkLock.notifyAll();
            }
        }

        private ImageView getAttachedImageView() {
            ImageView imageView = this.imageViewReference.get();
            if (this == ImageLoader.getBitmapWorkerTask(imageView)) {
                return imageView;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

        public AsyncDrawable(Resources resources, Bitmap bitmap, BitmapWorkerTask bitmapWorkerTask) {
            super(resources, bitmap);
            this.bitmapWorkerTaskReference = new WeakReference<>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBitmapWorkerTask() {
            return this.bitmapWorkerTaskReference.get();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(16)
    public void setImageBitmap(ImageView imageView, Bitmap bitmap) {
        if (this.mFadeInBitmap) {
            TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{new ColorDrawable(R.color.transparent), new BitmapDrawable(this.mResources, bitmap)});
            if (Build.VERSION.SDK_INT >= 16) {
                imageView.setBackground(imageView.getDrawable());
            } else {
                imageView.setBackgroundDrawable(imageView.getDrawable());
            }
            imageView.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(200);
            return;
        }
        imageView.setImageBitmap(bitmap);
    }

    public void setPauseWork(boolean z) {
        synchronized (this.mPauseWorkLock) {
            this.mPauseWork = z;
            if (!this.mPauseWork) {
                this.mPauseWorkLock.notifyAll();
            }
        }
    }

    public static Bitmap decodeSampledBitmapFromDescriptor(FileDescriptor fileDescriptor, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i5 = Math.round(i3 / i2);
            int round = Math.round(i4 / i);
            if (i5 >= round) {
                i5 = round;
            }
            while ((i4 * i3) / (i5 * i5) > i * i2 * 2) {
                i5++;
            }
        }
        return i5;
    }
}
