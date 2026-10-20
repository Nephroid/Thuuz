package defpackage;

import com.thuuz.gtv.ThuuzService;

/* loaded from: classes.dex */
public class bcg implements Runnable {
    final /* synthetic */ ThuuzService a;

    public bcg(ThuuzService thuuzService) {
        this.a = thuuzService;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.c(true);
    }
}
