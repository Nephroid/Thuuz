package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class jm implements ServiceConnection {
    final /* synthetic */ jl a;

    public jm(jl jlVar) {
        this.a = jlVar;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        HashMap hashMap;
        HashSet hashSet;
        hashMap = this.a.a.d;
        synchronized (hashMap) {
            this.a.g = iBinder;
            this.a.h = componentName;
            hashSet = this.a.d;
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                ((je) it.next()).onServiceConnected(componentName, iBinder);
            }
            this.a.e = 1;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        HashMap hashMap;
        HashSet hashSet;
        hashMap = this.a.a.d;
        synchronized (hashMap) {
            this.a.g = null;
            this.a.h = componentName;
            hashSet = this.a.d;
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                ((je) it.next()).onServiceDisconnected(componentName);
            }
            this.a.e = 2;
        }
    }
}
