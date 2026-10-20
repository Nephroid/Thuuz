package defpackage;

import com.thuuz.gtv.ThuuzService;

/* loaded from: classes.dex */
public class bcb implements Runnable {
    final /* synthetic */ ThuuzService a;

    public bcb(ThuuzService thuuzService) {
        this.a = thuuzService;
    }

    @Override // java.lang.Runnable
    public void run() {
        new Thread(new bcc(this)).start();
    }
}
