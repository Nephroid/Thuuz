package defpackage;

import com.thuuz.gtv.activity.nobar.SplashActivity;

/* loaded from: classes.dex */
public class blu implements Runnable {
    final /* synthetic */ SplashActivity a;

    public blu(SplashActivity splashActivity) {
        this.a = splashActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.e().info("had to force GUI thread.");
        new blz(this.a, null).execute(new Void[0]);
    }
}
