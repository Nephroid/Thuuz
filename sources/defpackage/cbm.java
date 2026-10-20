package defpackage;

import com.thuuz.gtv.view.HorizColumnScroll;

/* loaded from: classes.dex */
public class cbm implements Runnable {
    final /* synthetic */ HorizColumnScroll a;

    public cbm(HorizColumnScroll horizColumnScroll) {
        this.a = horizColumnScroll;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.g = true;
        this.a.invalidate();
        this.a.requestLayout();
    }
}
