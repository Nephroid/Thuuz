package defpackage;

import android.view.View;
import com.thuuz.gtv.settings.loc.TVProviderFragment;

/* loaded from: classes.dex */
public class bxc implements View.OnClickListener {
    final /* synthetic */ TVProviderFragment a;

    public bxc(TVProviderFragment tVProviderFragment) {
        this.a = tVProviderFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        cad.b(TVProviderFragment.access$100(this.a), "Hopper Troubleshooting", this.a.getResources().getString(bbt.hopper_trouble));
    }
}
