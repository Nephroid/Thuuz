package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class je implements ServiceConnection {
    final /* synthetic */ iz a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public je(iz izVar) {
        this.a = izVar;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.a.b(iBinder);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.a.a.sendMessage(this.a.a.obtainMessage(4, 1));
    }
}
