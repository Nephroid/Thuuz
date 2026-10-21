package defpackage;

import com.thuuz.gtv.activity.FeedActivity;

/* loaded from: classes.dex */
public class bea implements Runnable {
    final /* synthetic */ FeedActivity a;

    public bea(FeedActivity feedActivity) {
        this.a = feedActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.e().info("refreshing in onService after delay");
        this.a.c();
    }
}
