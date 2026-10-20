package defpackage;

import android.view.MotionEvent;
import android.view.View;
import com.thuuz.gtv.view.HorizColumnScroll;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class cbq implements View.OnTouchListener {
    final /* synthetic */ HorizColumnScroll a;

    private cbq(HorizColumnScroll horizColumnScroll) {
        this.a = horizColumnScroll;
    }

    public /* synthetic */ cbq(HorizColumnScroll horizColumnScroll, cbl cblVar) {
        this(horizColumnScroll);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        try {
        } catch (Throwable th) {
            this.a.getLogger().log(Level.SEVERE, "problem: ", th);
        }
        if (this.a.h) {
            this.a.h = false;
            return true;
        }
        if (this.a.a.onTouchEvent(motionEvent)) {
            return true;
        }
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.a.a(this.a.e, Math.round(this.a.getScrollX() / (this.a.getMeasuredWidth() / this.a.c)));
            return true;
        }
        if (motionEvent.getAction() == 0) {
            this.a.h();
        }
        return false;
    }
}
