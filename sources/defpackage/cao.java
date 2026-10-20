package defpackage;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/* loaded from: classes.dex */
public class cao implements bf {
    private final LruCache<String, Bitmap> a;

    public cao(LruCache<String, Bitmap> lruCache) {
        this.a = lruCache;
    }

    @Override // defpackage.bf
    public void a(String str, Bitmap bitmap) {
        this.a.put(str, bitmap);
    }

    @Override // defpackage.bf
    public Bitmap a(String str) {
        return this.a.get(str);
    }
}
