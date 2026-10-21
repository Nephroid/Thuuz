package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.activity.nobar.DialogActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bks extends BroadcastReceiver {
    final /* synthetic */ DialogActivity a;

    public bks(DialogActivity dialogActivity) {
        this.a = dialogActivity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            this.a.finish();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
