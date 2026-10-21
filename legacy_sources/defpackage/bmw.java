package defpackage;

import android.graphics.Bitmap;
import com.android.volley.VolleyError;

/* loaded from: classes.dex */
class bmw implements ai, aj<Bitmap> {
    final /* synthetic */ bms a;
    private final String b;

    public bmw(bms bmsVar, String str) {
        this.a = bmsVar;
        this.b = str;
    }

    @Override // defpackage.aj
    public void a(Bitmap bitmap) {
        cbb cbbVar;
        cbbVar = this.a.f;
        cbbVar.a(this.b, bitmap);
    }

    @Override // defpackage.ai
    public void a(VolleyError volleyError) {
        this.a.k().info("cannot get image: " + volleyError);
    }
}
