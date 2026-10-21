package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.activity.sports.AbstractTabActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bmk extends BroadcastReceiver {
    final /* synthetic */ AbstractTabActivity a;

    private bmk(AbstractTabActivity abstractTabActivity) {
        this.a = abstractTabActivity;
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
