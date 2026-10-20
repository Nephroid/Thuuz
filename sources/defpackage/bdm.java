package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bdm extends BroadcastReceiver {
    final /* synthetic */ AbstractActivity a;

    public bdm(AbstractActivity abstractActivity) {
        this.a = abstractActivity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            this.a.E();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
