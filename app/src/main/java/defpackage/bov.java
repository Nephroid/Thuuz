package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.sports.StandingsActivity;

/* loaded from: classes.dex */
public class bov implements View.OnKeyListener {
    final /* synthetic */ StandingsActivity a;

    public bov(StandingsActivity standingsActivity) {
        this.a = standingsActivity;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0011, code lost:
    
        r0 = false;
     */
    @Override // android.view.View.OnKeyListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onKey(android.view.View r6, int r7, android.view.KeyEvent r8) {
        /*
            r5 = this;
            r4 = 20
            r3 = 19
            r0 = 1
            r1 = 0
            int r2 = r8.getAction()     // Catch: java.lang.Throwable -> L21
            if (r2 != 0) goto Le
            if (r7 == r4) goto L12
        Le:
            if (r7 == r3) goto L12
            r0 = r1
        L11:
            return r0
        L12:
            if (r7 != r4) goto L31
            com.thuuz.gtv.activity.sports.StandingsActivity r2 = r5.a     // Catch: java.lang.Throwable -> L21
            android.webkit.WebView r2 = com.thuuz.gtv.activity.sports.StandingsActivity.a(r2)     // Catch: java.lang.Throwable -> L21
            r3 = 0
            r4 = 1500(0x5dc, float:2.102E-42)
            r2.flingScroll(r3, r4)     // Catch: java.lang.Throwable -> L21
            goto L11
        L21:
            r0 = move-exception
            com.thuuz.gtv.activity.sports.StandingsActivity r2 = r5.a
            java.util.logging.Logger r2 = r2.e()
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            java.lang.String r4 = "problem: "
            r2.log(r3, r4, r0)
        L2f:
            r0 = r1
            goto L11
        L31:
            if (r7 != r3) goto L2f
            com.thuuz.gtv.activity.sports.StandingsActivity r2 = r5.a     // Catch: java.lang.Throwable -> L21
            android.webkit.WebView r2 = com.thuuz.gtv.activity.sports.StandingsActivity.a(r2)     // Catch: java.lang.Throwable -> L21
            int r2 = r2.getScrollY()     // Catch: java.lang.Throwable -> L21
            if (r2 > 0) goto L41
            r0 = r1
            goto L11
        L41:
            com.thuuz.gtv.activity.sports.StandingsActivity r2 = r5.a     // Catch: java.lang.Throwable -> L21
            android.webkit.WebView r2 = com.thuuz.gtv.activity.sports.StandingsActivity.a(r2)     // Catch: java.lang.Throwable -> L21
            int r2 = r2.getScrollY()     // Catch: java.lang.Throwable -> L21
            r3 = 400(0x190, float:5.6E-43)
            if (r2 >= r3) goto L5b
            com.thuuz.gtv.activity.sports.StandingsActivity r2 = r5.a     // Catch: java.lang.Throwable -> L21
            android.webkit.WebView r2 = com.thuuz.gtv.activity.sports.StandingsActivity.a(r2)     // Catch: java.lang.Throwable -> L21
            r3 = 0
            r4 = 0
            r2.scrollTo(r3, r4)     // Catch: java.lang.Throwable -> L21
            goto L11
        L5b:
            com.thuuz.gtv.activity.sports.StandingsActivity r2 = r5.a     // Catch: java.lang.Throwable -> L21
            android.webkit.WebView r2 = com.thuuz.gtv.activity.sports.StandingsActivity.a(r2)     // Catch: java.lang.Throwable -> L21
            r3 = 0
            r4 = -1500(0xfffffffffffffa24, float:NaN)
            r2.flingScroll(r3, r4)     // Catch: java.lang.Throwable -> L21
            goto L11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bov.onKey(android.view.View, int, android.view.KeyEvent):boolean");
    }
}
