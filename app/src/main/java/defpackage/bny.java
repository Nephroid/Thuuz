package defpackage;

import android.os.Handler;
import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.view.HorizColumnScroll;

/* loaded from: classes.dex */
public class bny implements Runnable {
    final /* synthetic */ GuideActivity a;
    private final int b;
    private final int c;
    private final boolean d;

    public bny(GuideActivity guideActivity, int i, int i2, boolean z) {
        this.a = guideActivity;
        this.b = i;
        this.c = i2;
        this.d = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        HorizColumnScroll horizColumnScroll;
        HorizColumnScroll horizColumnScroll2;
        HorizColumnScroll horizColumnScroll3;
        HorizColumnScroll horizColumnScroll4;
        HorizColumnScroll horizColumnScroll5;
        Handler handler;
        HorizColumnScroll horizColumnScroll6;
        HorizColumnScroll horizColumnScroll7;
        HorizColumnScroll horizColumnScroll8;
        if (this.b > 8) {
            this.a.e().warning("after 8 attempts, giving up waiting and scrolling as best we can... ");
            horizColumnScroll8 = this.a.l;
            horizColumnScroll8.a(this.c, this.d);
            this.a.k = true;
            return;
        }
        if (this.a.d()) {
            horizColumnScroll = this.a.l;
            int size = horizColumnScroll.getSize();
            horizColumnScroll2 = this.a.l;
            if (size > horizColumnScroll2.e()) {
                horizColumnScroll3 = this.a.l;
                int measuredWidth = horizColumnScroll3.getContainer().getMeasuredWidth();
                horizColumnScroll4 = this.a.l;
                int columnWidth = size * horizColumnScroll4.getColumnWidth();
                if (Math.abs(measuredWidth - columnWidth) > 50) {
                    this.a.e().warning("after delay " + this.b + ", have hscroll width " + measuredWidth + " != sum of columns: " + columnWidth + "; trying again in 100 millis");
                    handler = this.a.m;
                    handler.postDelayed(new bny(this.a, this.b + 1, this.c, false), 100L);
                    return;
                } else {
                    this.a.e().info("scrollAttempt to: " + this.c);
                    horizColumnScroll5 = this.a.l;
                    horizColumnScroll5.a(this.c, this.d);
                    this.a.k = true;
                    return;
                }
            }
            this.a.e().info("scrollAttempt to: " + this.c);
            horizColumnScroll6 = this.a.l;
            horizColumnScroll6.a(this.c, this.d);
            this.a.k = true;
            return;
        }
        this.a.e().info("scrollAttempt to: " + this.c);
        horizColumnScroll7 = this.a.l;
        horizColumnScroll7.a(this.c, this.d);
        this.a.k = true;
    }
}
