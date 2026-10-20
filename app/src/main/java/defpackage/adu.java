package defpackage;

import android.content.DialogInterface;
import android.webkit.JsResult;

/* loaded from: classes.dex */
final class adu implements DialogInterface.OnCancelListener {
    final /* synthetic */ JsResult a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public adu(JsResult jsResult) {
        this.a = jsResult;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.a.cancel();
    }
}
