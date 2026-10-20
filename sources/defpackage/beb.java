package defpackage;

import com.thuuz.gtv.activity.FeedActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class beb implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ FeedActivity c;

    public beb(FeedActivity feedActivity, String str, String str2) {
        this.c = feedActivity;
        this.a = str;
        this.b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            int parseInt = Integer.parseInt(this.a);
            int i = parseInt <= 999 ? parseInt : 999;
            this.c.e().info("Feed newItemsCount = " + i + " for index: " + this.b);
            if (i == 0) {
                this.c.c(this.b, 0);
            } else {
                this.c.c(this.b, i);
            }
        } catch (Throwable th) {
            this.c.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
