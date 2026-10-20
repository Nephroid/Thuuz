package defpackage;

import com.android.volley.VolleyError;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bc implements ai {
    final /* synthetic */ String a;
    final /* synthetic */ ba b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bc(ba baVar, String str) {
        this.b = baVar;
        this.a = str;
    }

    @Override // defpackage.ai
    public void a(VolleyError volleyError) {
        this.b.a(this.a, volleyError);
    }
}
