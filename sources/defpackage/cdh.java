package defpackage;

import android.graphics.Paint;
import com.viewpagerindicator.UnderlinePageIndicator;

/* loaded from: classes.dex */
public class cdh implements Runnable {
    final /* synthetic */ UnderlinePageIndicator a;

    public cdh(UnderlinePageIndicator underlinePageIndicator) {
        this.a = underlinePageIndicator;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        Paint paint;
        int i;
        Paint paint2;
        z = this.a.b;
        if (z) {
            paint = this.a.a;
            int alpha = paint.getAlpha();
            i = this.a.e;
            int max = Math.max(alpha - i, 0);
            paint2 = this.a.a;
            paint2.setAlpha(max);
            this.a.invalidate();
            if (max > 0) {
                this.a.postDelayed(this, 30L);
            }
        }
    }
}
