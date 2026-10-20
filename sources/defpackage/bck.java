package defpackage;

import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.util.ServerChoice;
import com.thuuz.tv.TVDeviceManager;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bck implements Runnable {
    final /* synthetic */ ThuuzService a;

    public bck(ThuuzService thuuzService) {
        this.a = thuuzService;
    }

    @Override // java.lang.Runnable
    public void run() {
        bpu a;
        bqc m;
        boolean z;
        boolean z2;
        try {
        } catch (Throwable th) {
            this.a.H().log(Level.SEVERE, "problem refreshing channels from conn tv (dish): ", th);
        }
        if (this.a.m().bU() && (a = this.a.m().ax().a()) != null && (m = a.m()) != null) {
            TVDeviceManager tVDeviceManager = new TVDeviceManager(this.a.getFilesDir(), this.a.H(), ServerChoice.f());
            ThuuzService thuuzService = this.a;
            z = this.a.Q;
            thuuzService.Q = tVDeviceManager.a(!z);
            z2 = this.a.Q;
            if (!z2) {
                this.a.H().warning("cannot initialize channel libs");
                return;
            }
            List<String> a2 = tVDeviceManager.a(m.h());
            HashMap hashMap = new HashMap();
            for (String str : a2) {
                hashMap.put(str, str);
            }
            this.a.w().a(hashMap);
            this.a.P = null;
        }
    }
}
