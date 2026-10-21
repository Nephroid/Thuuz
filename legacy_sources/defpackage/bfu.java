package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.fantasy.FantasyTeamWebFragment;

/* loaded from: classes.dex */
public class bfu implements View.OnKeyListener {
    final /* synthetic */ FantasyTeamWebFragment a;

    public bfu(FantasyTeamWebFragment fantasyTeamWebFragment) {
        this.a = fantasyTeamWebFragment;
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0008, code lost:
    
        r2 = r5.a.getFragsActivity();
     */
    @Override // android.view.View.OnKeyListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onKey(android.view.View r6, int r7, android.view.KeyEvent r8) {
        /*
            r5 = this;
            r1 = 1
            r0 = 0
            int r2 = r8.getAction()     // Catch: java.lang.Throwable -> L34
            if (r2 != r1) goto L10
            com.thuuz.gtv.activity.fantasy.FantasyTeamWebFragment r2 = r5.a     // Catch: java.lang.Throwable -> L34
            com.thuuz.gtv.activity.AbstractActivity r2 = com.thuuz.gtv.activity.fantasy.FantasyTeamWebFragment.access$100(r2)     // Catch: java.lang.Throwable -> L34
            if (r2 != 0) goto L11
        L10:
            return r0
        L11:
            com.thuuz.gtv.App r2 = r2.j()     // Catch: java.lang.Throwable -> L34
            java.util.Set r2 = r2.O()     // Catch: java.lang.Throwable -> L34
            int r3 = r8.getKeyCode()     // Catch: java.lang.Throwable -> L34
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> L34
            boolean r2 = r2.contains(r3)     // Catch: java.lang.Throwable -> L34
            if (r2 != 0) goto L10
            bfw r2 = new bfw     // Catch: java.lang.Throwable -> L34
            com.thuuz.gtv.activity.fantasy.FantasyTeamWebFragment r3 = r5.a     // Catch: java.lang.Throwable -> L34
            r4 = 0
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L34
            r2.onClick(r6)     // Catch: java.lang.Throwable -> L34
            r0 = r1
            goto L10
        L34:
            r1 = move-exception
            com.thuuz.gtv.activity.fantasy.FantasyTeamWebFragment r2 = r5.a
            java.util.logging.Logger r2 = com.thuuz.gtv.activity.fantasy.FantasyTeamWebFragment.access$200(r2)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            java.lang.String r4 = "problem: "
            r2.log(r3, r4, r1)
            goto L10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bfu.onKey(android.view.View, int, android.view.KeyEvent):boolean");
    }
}
