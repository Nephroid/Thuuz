package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.google.android.gms.common.images.ImageManager;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
public final class iq implements Runnable {
    final /* synthetic */ ImageManager a;
    private final Uri b;
    private final ParcelFileDescriptor c;

    public iq(ImageManager imageManager, Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
        this.a = imageManager;
        this.b = uri;
        this.c = parcelFileDescriptor;
    }

    @Override // java.lang.Runnable
    public void run() {
        Handler handler;
        ix.b("LoadBitmapFromDiskRunnable can't be executed in the main thread");
        boolean z = false;
        Bitmap bitmap = null;
        if (this.c != null) {
            try {
                bitmap = BitmapFactory.decodeFileDescriptor(this.c.getFileDescriptor());
            } catch (OutOfMemoryError e) {
                Log.e("ImageManager", "OOM while loading bitmap for uri: " + this.b, e);
                z = true;
            }
            try {
                this.c.close();
            } catch (IOException e2) {
                Log.e("ImageManager", "closed failed", e2);
            }
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        handler = this.a.d;
        handler.post(new ir(this.a, this.b, bitmap, z, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e3) {
            Log.w("ImageManager", "Latch interrupted while posting " + this.b);
        }
    }
}
