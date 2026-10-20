package defpackage;

import com.viewpagerindicator.UnderlinePageIndicator;

/* loaded from: classes.dex */
public class cdi implements Runnable {
    final /* synthetic */ UnderlinePageIndicator a;

    public cdi(UnderlinePageIndicator underlinePageIndicator) {
        this.a = underlinePageIndicator;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        Runnable runnable;
        z = this.a.b;
        if (z) {
            UnderlinePageIndicator underlinePageIndicator = this.a;
            runnable = this.a.o;
            underlinePageIndicator.post(runnable);
        }
    }
}
