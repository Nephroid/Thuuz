package defpackage;

import com.thuuz.gtv.PlatformOperations;
import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bca implements Runnable {
    final /* synthetic */ PlatformOperations a;
    final /* synthetic */ AbstractActivity b;
    final /* synthetic */ ThuuzService c;

    public bca(ThuuzService thuuzService, PlatformOperations platformOperations, AbstractActivity abstractActivity) {
        this.c = thuuzService;
        this.a = platformOperations;
        this.b = abstractActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.initialActivityOnCreate(this.b, this.b.z());
        } catch (Throwable th) {
            this.c.H().log(Level.SEVERE, "problem initializing platform: ", th);
        }
    }
}
