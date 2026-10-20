package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import com.google.android.gms.common.images.ImageManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
public final class ir implements Runnable {
    final /* synthetic */ ImageManager a;
    private final Uri b;
    private final Bitmap c;
    private final CountDownLatch d;
    private boolean e;

    public ir(ImageManager imageManager, Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
        this.a = imageManager;
        this.b = uri;
        this.c = bitmap;
        this.e = z;
        this.d = countDownLatch;
    }

    private void a(ImageManager.ImageReceiver imageReceiver, boolean z) {
        ArrayList arrayList;
        Map map;
        Context context;
        aff affVar;
        Map map2;
        Context context2;
        arrayList = imageReceiver.c;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            is isVar = (is) arrayList.get(i);
            if (z) {
                context2 = this.a.c;
                isVar.a(context2, this.c, false);
            } else {
                map = this.a.j;
                map.put(this.b, Long.valueOf(SystemClock.elapsedRealtime()));
                context = this.a.c;
                affVar = this.a.g;
                isVar.a(context, affVar, false);
            }
            if (!(isVar instanceof iu)) {
                map2 = this.a.h;
                map2.remove(isVar);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        ip ipVar;
        Map map;
        Object obj;
        HashSet hashSet;
        ip ipVar2;
        ip ipVar3;
        Handler handler;
        ix.a("OnBitmapLoadedRunnable must be executed in the main thread");
        boolean z = this.c != null;
        ipVar = this.a.f;
        if (ipVar != null) {
            if (this.e) {
                ipVar3 = this.a.f;
                ipVar3.a();
                System.gc();
                this.e = false;
                handler = this.a.d;
                handler.post(this);
                return;
            }
            if (z) {
                ipVar2 = this.a.f;
                ipVar2.b(new it(this.b), this.c);
            }
        }
        map = this.a.i;
        ImageManager.ImageReceiver imageReceiver = (ImageManager.ImageReceiver) map.remove(this.b);
        if (imageReceiver != null) {
            a(imageReceiver, z);
        }
        this.d.countDown();
        obj = ImageManager.a;
        synchronized (obj) {
            hashSet = ImageManager.b;
            hashSet.remove(this.b);
        }
    }
}
