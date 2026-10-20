package defpackage;

import com.thuuz.gtv.view.HorizColumnScroll;

/* loaded from: classes.dex */
public class cbl implements Runnable {
    final /* synthetic */ HorizColumnScroll a;

    public cbl(HorizColumnScroll horizColumnScroll) {
        this.a = horizColumnScroll;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.invalidate();
        this.a.requestLayout();
    }
}
