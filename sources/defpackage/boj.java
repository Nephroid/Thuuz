package defpackage;

import com.thuuz.gtv.activity.sports.ShareActivity;

/* loaded from: classes.dex */
public class boj implements Runnable {
    final /* synthetic */ ShareActivity a;

    public boj(ShareActivity shareActivity) {
        this.a = shareActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.e().info("Problem associating Thuuz account.");
        this.a.aL();
    }
}
