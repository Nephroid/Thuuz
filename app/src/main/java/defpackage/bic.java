package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.friends.FriendFollowersFragment;
import com.thuuz.gtv.activity.friends.FriendsActivity;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bic extends AsyncTask<Void, Void, Integer> {
    bqb a;
    final /* synthetic */ FriendFollowersFragment b;

    public bic(FriendFollowersFragment friendFollowersFragment, bqb bqbVar) {
        this.b = friendFollowersFragment;
        this.a = bqbVar;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        Logger logger;
        AbstractActivity fragsActivity;
        try {
            fragsActivity = this.b.getFragsActivity();
            if (fragsActivity != null && !fragsActivity.isDestroyed()) {
                fragsActivity.e(true);
            }
        } catch (Throwable th) {
            logger = this.b.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        AbstractActivity fragsActivity;
        Logger logger;
        Logger logger2;
        if (this.a == null) {
            logger2 = this.b.getLogger();
            logger2.severe("cannot find friend in Follow");
            return -1;
        }
        fragsActivity = this.b.getFragsActivity();
        if (fragsActivity == null || fragsActivity.isDestroyed()) {
            return -1;
        }
        try {
            byv byvVar = new byv(fragsActivity.j(), this.a.a());
            byvVar.l();
            return Integer.valueOf(byvVar.v());
        } catch (Throwable th) {
            logger = this.b.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        AbstractActivity fragsActivity;
        Logger logger;
        fragsActivity = this.b.getFragsActivity();
        FriendsActivity friendsActivity = (FriendsActivity) fragsActivity;
        if (friendsActivity != null) {
            try {
                if (!friendsActivity.isDestroyed()) {
                    if (num.intValue() == 0) {
                        this.a.b(true);
                        if (friendsActivity.d()) {
                            friendsActivity.c();
                        }
                    } else if (friendsActivity.d()) {
                        friendsActivity.b("Failed to Follow", bbt.pls_try_again_later);
                    }
                }
            } catch (Throwable th) {
                logger = this.b.getLogger();
                logger.log(Level.SEVERE, "problem: ", th);
            } finally {
                friendsActivity.e(false);
            }
        }
    }
}
