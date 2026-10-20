package defpackage;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

/* loaded from: classes.dex */
final class ady implements DialogInterface.OnClickListener {
    final /* synthetic */ JsPromptResult a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ady(JsPromptResult jsPromptResult) {
        this.a = jsPromptResult;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.cancel();
    }
}
