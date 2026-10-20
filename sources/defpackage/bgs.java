package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.feed.FeedFragment;
import com.thuuz.gtv.activity.sports.ShareActivity;
import com.thuuz.gtv.settings.SettingsAccountActivity;

/* loaded from: classes.dex */
public class bgs implements View.OnKeyListener {
    final /* synthetic */ ShareActivity.SHARE_TYPE a;
    final /* synthetic */ SettingsAccountActivity.SOURCE b;
    final /* synthetic */ FeedFragment c;

    public bgs(FeedFragment feedFragment, ShareActivity.SHARE_TYPE share_type, SettingsAccountActivity.SOURCE source) {
        this.c = feedFragment;
        this.a = share_type;
        this.b = source;
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0008, code lost:
    
        r2 = r6.c.getFragsActivity();
     */
    @Override // android.view.View.OnKeyListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onKey(android.view.View r7, int r8, android.view.KeyEvent r9) {
        /*
            r6 = this;
            r1 = 1
            r0 = 0
            int r2 = r9.getAction()     // Catch: java.lang.Throwable -> L37
            if (r2 != r1) goto L10
            com.thuuz.gtv.activity.feed.FeedFragment r2 = r6.c     // Catch: java.lang.Throwable -> L37
            com.thuuz.gtv.activity.AbstractActivity r2 = com.thuuz.gtv.activity.feed.FeedFragment.access$000(r2)     // Catch: java.lang.Throwable -> L37
            if (r2 != 0) goto L11
        L10:
            return r0
        L11:
            com.thuuz.gtv.App r2 = r2.j()     // Catch: java.lang.Throwable -> L37
            java.util.Set r2 = r2.O()     // Catch: java.lang.Throwable -> L37
            int r3 = r9.getKeyCode()     // Catch: java.lang.Throwable -> L37
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> L37
            boolean r2 = r2.contains(r3)     // Catch: java.lang.Throwable -> L37
            if (r2 != 0) goto L10
            bgt r2 = new bgt     // Catch: java.lang.Throwable -> L37
            com.thuuz.gtv.activity.feed.FeedFragment r3 = r6.c     // Catch: java.lang.Throwable -> L37
            com.thuuz.gtv.activity.sports.ShareActivity$SHARE_TYPE r4 = r6.a     // Catch: java.lang.Throwable -> L37
            com.thuuz.gtv.settings.SettingsAccountActivity$SOURCE r5 = r6.b     // Catch: java.lang.Throwable -> L37
            r2.<init>(r3, r4, r5)     // Catch: java.lang.Throwable -> L37
            r2.onClick(r7)     // Catch: java.lang.Throwable -> L37
            r0 = r1
            goto L10
        L37:
            r1 = move-exception
            com.thuuz.gtv.activity.feed.FeedFragment r2 = r6.c
            java.util.logging.Logger r2 = com.thuuz.gtv.activity.feed.FeedFragment.access$100(r2)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            java.lang.String r4 = "problem: "
            r2.log(r3, r4, r1)
            goto L10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bgs.onKey(android.view.View, int, android.view.KeyEvent):boolean");
    }
}
