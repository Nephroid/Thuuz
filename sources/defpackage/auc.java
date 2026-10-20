package defpackage;

import android.util.Log;

/* loaded from: classes.dex */
class auc implements ask {
    private int a = 5;

    @Override // defpackage.ask
    public void a(String str) {
        if (this.a <= 6) {
            Log.e("GoogleTagManager", str);
        }
    }

    @Override // defpackage.ask
    public void a(String str, Throwable th) {
        if (this.a <= 6) {
            Log.e("GoogleTagManager", str, th);
        }
    }

    @Override // defpackage.ask
    public void b(String str) {
        if (this.a <= 5) {
            Log.w("GoogleTagManager", str);
        }
    }

    @Override // defpackage.ask
    public void c(String str) {
        if (this.a <= 4) {
            Log.i("GoogleTagManager", str);
        }
    }

    @Override // defpackage.ask
    public void d(String str) {
        if (this.a <= 2) {
            Log.v("GoogleTagManager", str);
        }
    }
}
