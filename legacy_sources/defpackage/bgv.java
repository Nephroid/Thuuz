package defpackage;

import android.webkit.WebView;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bgv extends bzy implements Runnable {
    private final int a;
    private final WebView b;
    private final WeakReference<AbstractActivity> c;
    private final int d;

    public bgv(int i, WebView webView, AbstractActivity abstractActivity, int i2) {
        this.a = i;
        this.b = webView;
        this.c = new WeakReference<>(abstractActivity);
        this.d = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.b != null) {
                if (this.a > 8) {
                    getLogger().warning("after 8 attempts, giving up waiting and scrolling as best we can... ");
                    this.b.scrollTo(0, this.d);
                } else {
                    AbstractActivity abstractActivity = this.c.get();
                    if (abstractActivity != null) {
                        if (!abstractActivity.d()) {
                            this.b.scrollTo(0, this.d);
                        } else {
                            this.b.scrollTo(0, this.d);
                            if (this.b.getScrollY() != this.d) {
                                abstractActivity.aa().postDelayed(new bgv(this.a + 1, this.b, abstractActivity, this.d), 100L);
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }
}
