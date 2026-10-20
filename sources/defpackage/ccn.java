package defpackage;

import android.view.View;
import com.viewpagerindicator.IconPageIndicator;

/* loaded from: classes.dex */
public class ccn implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ IconPageIndicator b;

    public ccn(IconPageIndicator iconPageIndicator, View view) {
        this.b = iconPageIndicator;
        this.a = view;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.smoothScrollTo(this.a.getLeft() - ((this.b.getWidth() - this.a.getWidth()) / 2), 0);
        this.b.d = null;
    }
}
