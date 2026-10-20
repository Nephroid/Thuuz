package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.activity.FeedActivity;
import com.thuuz.gtv.activity.feed.FeedFragment;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bdz extends BroadcastReceiver {
    final /* synthetic */ FeedActivity a;

    public bdz(FeedActivity feedActivity) {
        this.a = feedActivity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        bec becVar;
        bec becVar2;
        try {
            becVar = this.a.f;
            int count = becVar.getCount();
            for (int i = 0; i < count; i++) {
                becVar2 = this.a.f;
                FeedFragment a = becVar2.a(i);
                if (a != null) {
                    a.showHideCommentBox(this.a.k());
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
