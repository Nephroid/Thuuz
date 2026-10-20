package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.friends.FriendIncomingFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class big implements View.OnClickListener {
    final /* synthetic */ FriendIncomingFragment a;
    private bqb b;

    private big(FriendIncomingFragment friendIncomingFragment) {
        this.a = friendIncomingFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        try {
            this.b = (bqb) view.getTag();
            new bif(this.a, this.b).execute(new Void[0]);
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem clicking unfollow: ", th);
        }
    }
}
