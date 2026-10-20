package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.friends.AbstractFriendFragment;
import com.thuuz.gtv.activity.friends.FriendsActivity;

/* loaded from: classes.dex */
public class bgz implements View.OnClickListener {
    final /* synthetic */ AbstractFriendFragment a;

    public bgz(AbstractFriendFragment abstractFriendFragment) {
        this.a = abstractFriendFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AbstractActivity fragsActivity;
        fragsActivity = this.a.getFragsActivity();
        FriendsActivity friendsActivity = (FriendsActivity) fragsActivity;
        if (friendsActivity != null && !friendsActivity.isDestroyed()) {
            friendsActivity.aF();
        }
    }
}
