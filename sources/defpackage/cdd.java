package defpackage;

import android.content.Context;
import android.widget.TextView;
import com.viewpagerindicator.TabPageIndicator;

/* loaded from: classes.dex */
public class cdd extends TextView {
    final /* synthetic */ TabPageIndicator a;
    private int b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public cdd(TabPageIndicator tabPageIndicator, Context context) {
        super(context, null, ccu.vpiTabPageIndicatorStyle);
        this.a = tabPageIndicator;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public int a() {
        return this.b;
    }
}
