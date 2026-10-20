package defpackage;

import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/* loaded from: classes.dex */
class ahp extends akl {
    private Handler a;

    @Override // defpackage.akk
    public void a(Location location) {
        if (this.a == null) {
            Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = location;
        this.a.sendMessage(obtain);
    }
}
