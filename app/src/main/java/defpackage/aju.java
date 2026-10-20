package defpackage;

import android.view.View;

/* loaded from: classes.dex */
class aju implements View.OnClickListener {
    final /* synthetic */ ajx a;
    final /* synthetic */ ajr b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aju(ajr ajrVar, ajx ajxVar) {
        this.b = ajrVar;
        this.a = ajxVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.a();
    }
}
