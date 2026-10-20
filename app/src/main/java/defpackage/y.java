package defpackage;

import com.android.volley.VolleyError;

/* loaded from: classes.dex */
public class y implements al {
    private int a;
    private int b;
    private final int c;
    private final float d;

    public y() {
        this(2500, 1, 1.0f);
    }

    public y(int i, int i2, float f) {
        this.a = i;
        this.c = i2;
        this.d = f;
    }

    @Override // defpackage.al
    public int a() {
        return this.a;
    }

    @Override // defpackage.al
    public int b() {
        return this.b;
    }

    @Override // defpackage.al
    public void a(VolleyError volleyError) {
        this.b++;
        this.a = (int) (this.a + (this.a * this.d));
        if (!c()) {
            throw volleyError;
        }
    }

    protected boolean c() {
        return this.b <= this.c;
    }
}
