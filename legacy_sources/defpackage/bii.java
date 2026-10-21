package defpackage;

import com.thuuz.gtv.activity.friends.FriendsActivity;

/* loaded from: classes.dex */
public class bii implements Runnable {
    final /* synthetic */ FriendsActivity a;

    public bii(FriendsActivity friendsActivity) {
        this.a = friendsActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.e().info("Thuuz account has been associated with FB.");
        this.a.aL();
    }
}
