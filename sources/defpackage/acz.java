package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class acz extends BroadcastReceiver {
    private acz() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ acz(acy acyVar) {
        this();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
            boolean unused = acx.c = true;
        } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            boolean unused2 = acx.c = false;
        }
    }
}
