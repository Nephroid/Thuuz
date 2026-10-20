package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.nobar.OnboardPage4TvActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;

/* loaded from: classes.dex */
public class bxq implements View.OnClickListener {
    final /* synthetic */ TVProviderFragment a;

    public bxq(TVProviderFragment tVProviderFragment) {
        this.a = tVProviderFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AbstractActivity fragsActivity;
        fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null && (fragsActivity instanceof OnboardPage4TvActivity)) {
            ((OnboardPage4TvActivity) fragsActivity).aF();
        }
    }
}
