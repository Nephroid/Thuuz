package defpackage;

import android.support.v4.view.ViewPager;
import android.view.View;
import com.viewpagerindicator.TabPageIndicator;

/* loaded from: classes.dex */
public class cda implements View.OnClickListener {
    final /* synthetic */ TabPageIndicator a;

    public cda(TabPageIndicator tabPageIndicator) {
        this.a = tabPageIndicator;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewPager viewPager;
        ViewPager viewPager2;
        cdc cdcVar;
        cdc cdcVar2;
        viewPager = this.a.e;
        int currentItem = viewPager.getCurrentItem();
        int a = ((cdd) view).a();
        viewPager2 = this.a.e;
        viewPager2.setCurrentItem(a);
        if (currentItem == a) {
            cdcVar = this.a.h;
            if (cdcVar != null) {
                cdcVar2 = this.a.h;
                cdcVar2.a(a);
            }
        }
    }
}
