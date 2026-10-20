package defpackage;

import android.content.SharedPreferences;

/* loaded from: classes.dex */
final class atn implements Runnable {
    final /* synthetic */ SharedPreferences.Editor a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public atn(SharedPreferences.Editor editor) {
        this.a = editor;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.commit();
    }
}
