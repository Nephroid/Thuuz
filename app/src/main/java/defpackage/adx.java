package defpackage;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

/* loaded from: classes.dex */
final class adx implements DialogInterface.OnCancelListener {
    final /* synthetic */ JsPromptResult a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public adx(JsPromptResult jsPromptResult) {
        this.a = jsPromptResult;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.a.cancel();
    }
}
