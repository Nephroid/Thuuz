package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@zy
/* loaded from: classes.dex */
public class yc extends acq implements ServiceConnection {
    private final Object a = new Object();
    private boolean b = false;
    private Context c;
    private zd d;
    private yb e;
    private yj f;
    private List<yh> g;
    private ym h;

    public yc(Context context, zd zdVar, ym ymVar) {
        this.g = null;
        this.c = context;
        this.d = zdVar;
        this.h = ymVar;
        this.e = new yb(context);
        this.f = yj.a(this.c);
        this.g = this.f.a(10L);
    }

    private void a(long j) {
        do {
            if (!b(j)) {
                adn.e("Timeout waiting for pending transaction to be processed.");
            }
        } while (!this.b);
    }

    private void a(yh yhVar, String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("RESPONSE_CODE", 0);
        intent.putExtra("INAPP_PURCHASE_DATA", str);
        intent.putExtra("INAPP_DATA_SIGNATURE", str2);
        adm.a.post(new yd(this, yhVar, intent));
    }

    private boolean b(long j) {
        long elapsedRealtime = 60000 - (SystemClock.elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.a.wait(elapsedRealtime);
        } catch (InterruptedException e) {
            adn.e("waitWithTimeout_lock interrupted");
        }
        return true;
    }

    private void c() {
        if (this.g.isEmpty()) {
            return;
        }
        HashMap hashMap = new HashMap();
        for (yh yhVar : this.g) {
            hashMap.put(yhVar.c, yhVar);
        }
        String str = null;
        while (true) {
            Bundle b = this.e.b(this.c.getPackageName(), str);
            if (b == null || yl.a(b) != 0) {
                break;
            }
            ArrayList<String> stringArrayList = b.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
            ArrayList<String> stringArrayList2 = b.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
            ArrayList<String> stringArrayList3 = b.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
            String string = b.getString("INAPP_CONTINUATION_TOKEN");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= stringArrayList.size()) {
                    break;
                }
                if (hashMap.containsKey(stringArrayList.get(i2))) {
                    String str2 = stringArrayList.get(i2);
                    String str3 = stringArrayList2.get(i2);
                    String str4 = stringArrayList3.get(i2);
                    yh yhVar2 = (yh) hashMap.get(str2);
                    if (yhVar2.b.equals(yl.a(str3))) {
                        a(yhVar2, str3, str4);
                        hashMap.remove(str2);
                    }
                }
                i = i2 + 1;
            }
            if (string == null || hashMap.isEmpty()) {
                break;
            } else {
                str = string;
            }
        }
        Iterator it = hashMap.keySet().iterator();
        while (it.hasNext()) {
            this.f.a((yh) hashMap.get((String) it.next()));
        }
    }

    @Override // defpackage.acq
    public void a() {
        synchronized (this.a) {
            Context context = this.c;
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            Context context2 = this.c;
            context.bindService(intent, this, 1);
            a(SystemClock.elapsedRealtime());
            this.c.unbindService(this);
            this.e.a();
        }
    }

    @Override // defpackage.acq
    public void b() {
        synchronized (this.a) {
            this.c.unbindService(this);
            this.e.a();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.a) {
            this.e.a(iBinder);
            c();
            this.b = true;
            this.a.notify();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        adn.c("In-app billing service disconnected.");
        this.e.a();
    }
}
