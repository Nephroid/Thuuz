package defpackage;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.ViewSwitcher;

@zy
/* loaded from: classes.dex */
final class ajv extends ViewSwitcher {
    private final adc a;

    public ajv(Context context) {
        super(context);
        this.a = new adc(context);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.a.a(motionEvent);
        return false;
    }
}
