package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.friends.SearchFriendsActivity;

/* loaded from: classes.dex */
public class bip implements View.OnClickListener {
    final /* synthetic */ SearchFriendsActivity a;

    public bip(SearchFriendsActivity searchFriendsActivity) {
        this.a = searchFriendsActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.aN();
    }
}
