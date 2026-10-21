package defpackage;

import android.graphics.Bitmap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bb implements aj<Bitmap> {
    final /* synthetic */ String a;
    final /* synthetic */ ba b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bb(ba baVar, String str) {
        this.b = baVar;
        this.a = str;
    }

    @Override // defpackage.aj
    public void a(Bitmap bitmap) {
        this.b.a(this.a, bitmap);
    }
}
