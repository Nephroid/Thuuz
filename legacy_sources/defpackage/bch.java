package defpackage;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Handler;
import com.thuuz.gtv.ThuuzService;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bch implements Runnable {
    final /* synthetic */ ThuuzService a;

    public bch(ThuuzService thuuzService) {
        this.a = thuuzService;
    }

    @Override // java.lang.Runnable
    public void run() {
        bqr bqrVar;
        Handler handler;
        bqr bqrVar2;
        bqr bqrVar3;
        try {
            bqrVar = this.a.D;
            if (bqrVar == null) {
                ThuuzService thuuzService = this.a;
                handler = this.a.h;
                thuuzService.D = new bqr(handler, this.a.getContentResolver(), this.a.m());
                ContentResolver contentResolver = this.a.m().getContentResolver();
                Uri uri = bqr.b;
                bqrVar2 = this.a.D;
                contentResolver.registerContentObserver(uri, true, bqrVar2);
                bqrVar3 = this.a.D;
                bqrVar3.a();
            }
        } catch (Throwable th) {
            this.a.H().log(Level.SEVERE, "problem adding media selection listener: ", th);
        }
    }
}
