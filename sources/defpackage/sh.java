package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
class sh extends BroadcastReceiver {
    final /* synthetic */ sd a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public sh(sd sdVar) {
        this.a = sdVar;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.a.b(false);
    }
}
