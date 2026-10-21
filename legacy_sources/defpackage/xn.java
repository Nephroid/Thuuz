package defpackage;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

@zy
/* loaded from: classes.dex */
public final class xn extends RelativeLayout {
    private final adc a;

    public xn(Context context, String str) {
        super(context);
        this.a = new adc(context, str);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.a.a(motionEvent);
        return false;
    }
}
