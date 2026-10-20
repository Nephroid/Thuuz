package defpackage;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ba {
    private final ag a;
    private final bf c;
    private Runnable g;
    private int b = 100;
    private final HashMap<String, be> d = new HashMap<>();
    private final HashMap<String, be> e = new HashMap<>();
    private final Handler f = new Handler(Looper.getMainLooper());

    public ba(ag agVar, bf bfVar) {
        this.a = agVar;
        this.c = bfVar;
    }

    public bg a(String str, bh bhVar, int i, int i2) {
        a();
        String a = a(str, i, i2);
        Bitmap a2 = this.c.a(a);
        if (a2 != null) {
            bg bgVar = new bg(this, a2, str, null, null);
            bhVar.a(bgVar, true);
            return bgVar;
        }
        bg bgVar2 = new bg(this, null, str, a, bhVar);
        bhVar.a(bgVar2, true);
        be beVar = this.d.get(a);
        if (beVar != null) {
            beVar.a(bgVar2);
            return bgVar2;
        }
        Request<Bitmap> a3 = a(str, i, i2, a);
        this.a.a(a3);
        this.d.put(a, new be(this, a3, bgVar2));
        return bgVar2;
    }

    protected Request<Bitmap> a(String str, int i, int i2, String str2) {
        return new bi(str, new bb(this, str2), i, i2, Bitmap.Config.RGB_565, new bc(this, str2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str, Bitmap bitmap) {
        this.c.a(str, bitmap);
        be remove = this.d.remove(str);
        if (remove != null) {
            be.a(remove, bitmap);
            a(str, remove);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str, VolleyError volleyError) {
        be remove = this.d.remove(str);
        if (remove != null) {
            remove.a(volleyError);
            a(str, remove);
        }
    }

    private void a(String str, be beVar) {
        this.e.put(str, beVar);
        if (this.g == null) {
            this.g = new bd(this);
            this.f.postDelayed(this.g, this.b);
        }
    }

    private void a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        }
    }

    private static String a(String str, int i, int i2) {
        return new StringBuilder(str.length() + 12).append("#W").append(i).append("#H").append(i2).append(str).toString();
    }
}
