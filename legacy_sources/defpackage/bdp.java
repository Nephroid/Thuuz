package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;

/* loaded from: classes.dex */
public class bdp implements View.OnClickListener {
    final /* synthetic */ AbstractActivity a;

    public bdp(AbstractActivity abstractActivity) {
        this.a = abstractActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.O();
    }
}
