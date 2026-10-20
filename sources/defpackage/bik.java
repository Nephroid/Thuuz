package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.activity.friends.FriendsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bik extends BroadcastReceiver {
    final /* synthetic */ FriendsActivity a;

    private bik(FriendsActivity friendsActivity) {
        this.a = friendsActivity;
    }

    public /* synthetic */ bik(FriendsActivity friendsActivity, bih bihVar) {
        this(friendsActivity);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.a.e().info("received fantasy sync broadcast");
        try {
            this.a.e(intent.getIntExtra("result", -1));
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem in onReceive for fantasy sync: ", th);
        }
    }
}
