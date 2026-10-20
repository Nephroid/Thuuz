package defpackage;

import android.view.View;

/* loaded from: classes.dex */
class bhb implements View.OnClickListener {
    final /* synthetic */ bha a;

    private bhb(bha bhaVar) {
        this.a = bhaVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ bhb(bha bhaVar, bgy bgyVar) {
        this(bhaVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001c, code lost:
    
        r1 = r4.a.a.getFragsActivity();
     */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onClick(android.view.View r5) {
        /*
            r4 = this;
            bha r0 = r4.a     // Catch: java.lang.Throwable -> L3c
            com.thuuz.gtv.activity.friends.AbstractFriendFragment r0 = r0.a     // Catch: java.lang.Throwable -> L3c
            android.widget.ListView r0 = com.thuuz.gtv.activity.friends.AbstractFriendFragment.access$600(r0)     // Catch: java.lang.Throwable -> L3c
            int r0 = r0.getPositionForView(r5)     // Catch: java.lang.Throwable -> L3c
            r1 = -1
            if (r0 != r1) goto L10
        Lf:
            return
        L10:
            bha r1 = r4.a     // Catch: java.lang.Throwable -> L3c
            com.thuuz.gtv.activity.friends.AbstractFriendFragment r1 = r1.a     // Catch: java.lang.Throwable -> L3c
            java.util.List<bqb> r1 = r1.friends     // Catch: java.lang.Throwable -> L3c
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L3c
            if (r1 <= r0) goto Lf
            bha r0 = r4.a     // Catch: java.lang.Throwable -> L3c
            com.thuuz.gtv.activity.friends.AbstractFriendFragment r0 = r0.a     // Catch: java.lang.Throwable -> L3c
            com.thuuz.gtv.activity.AbstractActivity r1 = com.thuuz.gtv.activity.friends.AbstractFriendFragment.access$700(r0)     // Catch: java.lang.Throwable -> L3c
            if (r1 == 0) goto Lf
            com.thuuz.gtv.App r0 = r1.j()     // Catch: java.lang.Throwable -> L3c
            boolean r0 = r0.e()     // Catch: java.lang.Throwable -> L3c
            if (r0 == 0) goto L4d
            bha r0 = r4.a     // Catch: java.lang.Throwable -> L3c
            com.thuuz.gtv.activity.friends.AbstractFriendFragment r0 = r0.a     // Catch: java.lang.Throwable -> L3c
            android.view.View$OnClickListener r0 = r0.getButtonClickHandler()     // Catch: java.lang.Throwable -> L3c
            r0.onClick(r5)     // Catch: java.lang.Throwable -> L3c
            goto Lf
        L3c:
            r0 = move-exception
            bha r1 = r4.a
            com.thuuz.gtv.activity.friends.AbstractFriendFragment r1 = r1.a
            java.util.logging.Logger r1 = com.thuuz.gtv.activity.friends.AbstractFriendFragment.access$800(r1)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "Cannot handle: "
            r1.log(r2, r3, r0)
            goto Lf
        L4d:
            java.lang.Object r0 = r5.getTag()     // Catch: java.lang.Throwable -> L3c
            bqb r0 = (defpackage.bqb) r0     // Catch: java.lang.Throwable -> L3c
            int r0 = r0.a()     // Catch: java.lang.Throwable -> L3c
            r1.d(r0)     // Catch: java.lang.Throwable -> L3c
            goto Lf
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bhb.onClick(android.view.View):void");
    }
}
