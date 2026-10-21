package defpackage;

import android.graphics.Bitmap;
import java.util.HashMap;

/* loaded from: classes.dex */
public class bg {
    final /* synthetic */ ba a;
    private Bitmap b;
    private final bh c;
    private final String d;
    private final String e;

    public bg(ba baVar, Bitmap bitmap, String str, String str2, bh bhVar) {
        this.a = baVar;
        this.b = bitmap;
        this.e = str;
        this.d = str2;
        this.c = bhVar;
    }

    public void a() {
        HashMap hashMap;
        HashMap hashMap2;
        HashMap hashMap3;
        HashMap hashMap4;
        if (this.c != null) {
            hashMap = this.a.d;
            be beVar = (be) hashMap.get(this.d);
            if (beVar == null) {
                hashMap2 = this.a.e;
                be beVar2 = (be) hashMap2.get(this.d);
                if (beVar2 != null) {
                    beVar2.b(this);
                    if (be.a(beVar2).size() == 0) {
                        hashMap3 = this.a.e;
                        hashMap3.remove(this.d);
                        return;
                    }
                    return;
                }
                return;
            }
            if (beVar.b(this)) {
                hashMap4 = this.a.d;
                hashMap4.remove(this.d);
            }
        }
    }

    public Bitmap b() {
        return this.b;
    }

    public String c() {
        return this.e;
    }
}
