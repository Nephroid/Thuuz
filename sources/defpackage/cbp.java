package defpackage;

import android.view.GestureDetector;
import com.thuuz.gtv.view.HorizColumnScroll;

/* loaded from: classes.dex */
public class cbp extends GestureDetector.SimpleOnGestureListener {
    final /* synthetic */ HorizColumnScroll a;

    private cbp(HorizColumnScroll horizColumnScroll) {
        this.a = horizColumnScroll;
    }

    public /* synthetic */ cbp(HorizColumnScroll horizColumnScroll, cbl cblVar) {
        this(horizColumnScroll);
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0011, code lost:
    
        r0 = false;
     */
    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onFling(android.view.MotionEvent r9, android.view.MotionEvent r10, float r11, float r12) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cbp.onFling(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
    }

    private int a(int i) {
        int i2 = i - 1;
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 != this.a.e) {
            this.a.q = true;
        }
        return i2;
    }

    private int b(int i) {
        int maxColumnThatCanBeLeftmost;
        maxColumnThatCanBeLeftmost = this.a.getMaxColumnThatCanBeLeftmost();
        int i2 = i + 1;
        if (i2 <= maxColumnThatCanBeLeftmost) {
            maxColumnThatCanBeLeftmost = i2;
        }
        if (maxColumnThatCanBeLeftmost != this.a.e) {
            this.a.q = true;
        }
        return maxColumnThatCanBeLeftmost;
    }
}
