package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.friends.FriendFollowersFragment;

/* loaded from: classes.dex */
public class bid implements View.OnClickListener {
    final /* synthetic */ FriendFollowersFragment a;

    private bid(FriendFollowersFragment friendFollowersFragment) {
        this.a = friendFollowersFragment;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0008, code lost:
    
        r1 = r4.a.getFragsActivity();
     */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onClick(android.view.View r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r5.getTag()     // Catch: java.lang.Throwable -> L2f
            bqb r0 = (defpackage.bqb) r0     // Catch: java.lang.Throwable -> L2f
            if (r0 == 0) goto L16
            com.thuuz.gtv.activity.friends.FriendFollowersFragment r1 = r4.a     // Catch: java.lang.Throwable -> L2f
            com.thuuz.gtv.activity.AbstractActivity r1 = com.thuuz.gtv.activity.friends.FriendFollowersFragment.access$100(r1)     // Catch: java.lang.Throwable -> L2f
            if (r1 == 0) goto L16
            boolean r2 = r1.isDestroyed()     // Catch: java.lang.Throwable -> L2f
            if (r2 == 0) goto L17
        L16:
            return
        L17:
            com.thuuz.gtv.App r2 = r1.j()     // Catch: java.lang.Throwable -> L2f
            boolean r2 = r2.e()     // Catch: java.lang.Throwable -> L2f
            if (r2 == 0) goto L3e
            boolean r2 = r0.c()     // Catch: java.lang.Throwable -> L2f
            if (r2 == 0) goto L3e
            int r0 = r0.a()     // Catch: java.lang.Throwable -> L2f
            r1.d(r0)     // Catch: java.lang.Throwable -> L2f
            goto L16
        L2f:
            r0 = move-exception
            com.thuuz.gtv.activity.friends.FriendFollowersFragment r1 = r4.a
            java.util.logging.Logger r1 = com.thuuz.gtv.activity.friends.FriendFollowersFragment.access$200(r1)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "problem clicking unfollow: "
            r1.log(r2, r3, r0)
            goto L16
        L3e:
            bic r1 = new bic     // Catch: java.lang.Throwable -> L2f
            com.thuuz.gtv.activity.friends.FriendFollowersFragment r2 = r4.a     // Catch: java.lang.Throwable -> L2f
            r1.<init>(r2, r0)     // Catch: java.lang.Throwable -> L2f
            r0 = 0
            java.lang.Void[] r0 = new java.lang.Void[r0]     // Catch: java.lang.Throwable -> L2f
            r1.execute(r0)     // Catch: java.lang.Throwable -> L2f
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bid.onClick(android.view.View):void");
    }
}
