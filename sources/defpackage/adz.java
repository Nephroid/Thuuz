package defpackage;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.widget.EditText;

/* loaded from: classes.dex */
final class adz implements DialogInterface.OnClickListener {
    final /* synthetic */ JsPromptResult a;
    final /* synthetic */ EditText b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public adz(JsPromptResult jsPromptResult, EditText editText) {
        this.a = jsPromptResult;
        this.b = editText;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.confirm(this.b.getText().toString());
    }
}
