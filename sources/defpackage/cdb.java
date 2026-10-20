package defpackage;

import android.view.View;
import com.viewpagerindicator.TabPageIndicator;

/* loaded from: classes.dex */
public class cdb implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ TabPageIndicator b;

    public cdb(TabPageIndicator tabPageIndicator, View view) {
        this.b = tabPageIndicator;
        this.a = view;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.smoothScrollTo(this.a.getLeft() - ((this.b.getWidth() - this.a.getWidth()) / 2), 0);
        this.b.b = null;
    }
}
