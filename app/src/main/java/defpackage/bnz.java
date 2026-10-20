package defpackage;

import android.os.Handler;
import android.view.View;
import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.view.HorizColumnScroll;
import com.thuuz.gtv.view.ListViewDpad;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bnz implements Runnable {
    final /* synthetic */ GuideActivity a;
    private boolean b;
    private int c;
    private final Integer d;

    public bnz(GuideActivity guideActivity, boolean z) {
        this.a = guideActivity;
        this.c = 0;
        this.d = null;
        this.b = z;
    }

    private bnz(GuideActivity guideActivity, int i, Integer num, boolean z) {
        this.a = guideActivity;
        this.c = 0;
        this.c = i;
        this.d = num;
        this.b = z;
    }

    private void a() {
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        HorizColumnScroll horizColumnScroll;
        HorizColumnScroll horizColumnScroll2;
        HorizColumnScroll horizColumnScroll3;
        HorizColumnScroll horizColumnScroll4;
        int g;
        HorizColumnScroll horizColumnScroll5;
        Handler handler;
        Handler handler2;
        boolean z2 = false;
        try {
            if (!this.a.isDestroyed()) {
                z = this.a.k;
                if (z) {
                    a();
                    return;
                }
                if (this.c > 8) {
                    this.a.g(this.d);
                    a();
                    return;
                }
                horizColumnScroll = this.a.l;
                int size = horizColumnScroll.getSize();
                horizColumnScroll2 = this.a.l;
                if (size <= horizColumnScroll2.e()) {
                    this.a.g(this.d);
                    a();
                    return;
                }
                horizColumnScroll3 = this.a.l;
                int measuredWidth = horizColumnScroll3.getContainer().getMeasuredWidth();
                horizColumnScroll4 = this.a.l;
                int columnWidth = size * horizColumnScroll4.getColumnWidth();
                if (Math.abs(measuredWidth - columnWidth) <= 50) {
                    g = this.a.g(this.d);
                    if (this.a.j().e()) {
                        horizColumnScroll5 = this.a.l;
                        ListViewDpad a = horizColumnScroll5.a(g);
                        if (a == null) {
                            this.a.e().warning("cannot find list for target focus: " + g);
                            return;
                        }
                        View childAt = a.getChildAt(0);
                        if (childAt != null && childAt.hasFocus()) {
                            z2 = true;
                        }
                        if (!z2) {
                            this.a.e().warning("after foc-ini delay " + this.c + ", focus not found; trying again in 100 millis");
                            handler = this.a.m;
                            handler.postDelayed(new bnz(this.a, this.c + 1, this.d, this.b), 100L);
                            return;
                        }
                        return;
                    }
                    return;
                }
                this.a.e().warning("after foc-ini delay " + this.c + ", have hscroll width: " + measuredWidth + " != sum of columns: " + columnWidth + "; trying again in 100 millis");
                handler2 = this.a.m;
                handler2.postDelayed(new bnz(this.a, this.c + 1, this.d, this.b), 100L);
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
