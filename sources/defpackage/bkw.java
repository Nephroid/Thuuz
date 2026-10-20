package defpackage;

import com.thuuz.gtv.activity.nobar.MP4VideoActivity;

/* loaded from: classes.dex */
public class bkw implements Runnable {
    final /* synthetic */ MP4VideoActivity a;

    public bkw(MP4VideoActivity mP4VideoActivity) {
        this.a = mP4VideoActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.aI();
    }
}
