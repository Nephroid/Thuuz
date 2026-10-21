package defpackage;

import android.os.IBinder;

/* loaded from: classes.dex */
class of implements od {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public of(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.a;
    }
}
