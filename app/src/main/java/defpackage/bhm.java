package defpackage;

import com.facebook.FacebookRequestError;
import com.facebook.model.GraphUser;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.friends.AddFbFriendsActivity;

/* loaded from: classes.dex */
public class bhm extends bqg {
    public bhm(AbstractActivity abstractActivity) {
        super(abstractActivity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bqg
    public void a(GraphUser graphUser) {
        super.a(graphUser);
        String bd = c().bd();
        if (bd == null || bd.length() == 0) {
            getLogger().severe("AddFbFriends profile success finds empty social id!");
            return;
        }
        AddFbFriendsActivity addFbFriendsActivity = (AddFbFriendsActivity) b();
        if (addFbFriendsActivity != null) {
            if (!c().aZ()) {
                getLogger().info("attempting associate for fb user: " + graphUser);
                addFbFriendsActivity.a(bd, false);
                return;
            }
            String bc = c().bc();
            if (bc.equals(bd)) {
                addFbFriendsActivity.aK();
            } else {
                getLogger().info("attempting re-associate for fb user: " + graphUser);
                a(bd, addFbFriendsActivity, bc);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bqg
    public void a(FacebookRequestError facebookRequestError) {
        AbstractActivity b = b();
        if (b != null) {
            b.c("Failed to Connect", "Facebook says: " + b.a(facebookRequestError));
            b.finish();
        }
    }
}
