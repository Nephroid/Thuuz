package defpackage;

import com.thuuz.gtv.activity.friends.SearchFriendsActivity;

/* loaded from: classes.dex */
public class biq implements Runnable {
    final /* synthetic */ SearchFriendsActivity a;

    public biq(SearchFriendsActivity searchFriendsActivity) {
        this.a = searchFriendsActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.f(true);
    }
}
