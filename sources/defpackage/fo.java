package defpackage;

import android.util.Log;

/* loaded from: classes.dex */
class fo implements eh {
    private int a = 2;

    private String e(String str) {
        return Thread.currentThread().toString() + ": " + str;
    }

    @Override // defpackage.eh
    public int a() {
        return this.a;
    }

    @Override // defpackage.eh
    public void a(int i) {
        this.a = i;
    }

    @Override // defpackage.eh
    public void a(String str) {
        if (this.a <= 0) {
            Log.v("GAV4", e(str));
        }
    }

    @Override // defpackage.eh
    public void b(String str) {
        if (this.a <= 1) {
            Log.i("GAV4", e(str));
        }
    }

    @Override // defpackage.eh
    public void c(String str) {
        if (this.a <= 2) {
            Log.w("GAV4", e(str));
        }
    }

    @Override // defpackage.eh
    public void d(String str) {
        if (this.a <= 3) {
            Log.e("GAV4", e(str));
        }
    }
}
