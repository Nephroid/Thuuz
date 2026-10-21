package defpackage;
// [removed legacy tracker import]
// [removed legacy tracker import]
import com.thuuz.gtv.activity.friends.AddFriendsFragment;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bhq implements Session.StatusCallback {
    final /* synthetic */ AddFriendsFragment a;

    public bhq(AddFriendsFragment addFriendsFragment) {
        this.a = addFriendsFragment;
    }

    @Override // com.facebook.Session.StatusCallback
    public void call(Session session, SessionState sessionState, Exception exc) {
        Logger logger;
        logger = this.a.getLogger();
        logger.info("fb session state: " + sessionState);
    }
}
