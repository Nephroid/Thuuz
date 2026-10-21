package defpackage;

import com.thuuz.gtv.activity.friends.FriendsActivity;

/* loaded from: classes.dex */
public class bij implements Runnable {
    final /* synthetic */ FriendsActivity a;

    public bij(FriendsActivity friendsActivity) {
        this.a = friendsActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.e().info("Problem associating Thuuz account.");
        this.a.aR();
    }
}
