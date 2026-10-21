package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.friends.SearchFriendsActivity;

/* loaded from: classes.dex */
public class bin implements View.OnClickListener {
    final /* synthetic */ SearchFriendsActivity a;

    public bin(SearchFriendsActivity searchFriendsActivity) {
        this.a = searchFriendsActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.aL();
    }
}
