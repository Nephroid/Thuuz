package defpackage;

import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes.dex */
class ajt implements View.OnTouchListener {
    final /* synthetic */ ajx a;
    final /* synthetic */ ajr b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ajt(ajr ajrVar, ajx ajxVar) {
        this.b = ajrVar;
        this.a = ajxVar;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.a.a();
        return false;
    }
}
