package defpackage;

import android.graphics.Bitmap;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import java.util.LinkedList;

/* loaded from: classes.dex */
public class be {
    final /* synthetic */ ba a;
    private final Request<?> b;
    private Bitmap c;
    private VolleyError d;
    private final LinkedList<bg> e = new LinkedList<>();

    public static /* synthetic */ Bitmap a(be beVar, Bitmap bitmap) {
        beVar.c = bitmap;
        return bitmap;
    }

    public be(ba baVar, Request<?> request, bg bgVar) {
        this.a = baVar;
        this.b = request;
        this.e.add(bgVar);
    }

    public void a(VolleyError volleyError) {
        this.d = volleyError;
    }

    public VolleyError a() {
        return this.d;
    }

    public void a(bg bgVar) {
        this.e.add(bgVar);
    }

    public boolean b(bg bgVar) {
        this.e.remove(bgVar);
        if (this.e.size() != 0) {
            return false;
        }
        this.b.f();
        return true;
    }
}
