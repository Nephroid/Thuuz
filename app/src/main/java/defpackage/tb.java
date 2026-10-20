package defpackage;

import android.webkit.ValueCallback;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class tb implements ValueCallback<String> {
    final /* synthetic */ ta a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public tb(ta taVar) {
        this.a = taVar;
    }

    @Override // android.webkit.ValueCallback
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onReceiveValue(String str) {
        this.a.d.a(this.a.b, this.a.c, str);
    }
}
