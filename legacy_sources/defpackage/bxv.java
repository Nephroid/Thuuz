package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.settings.loc.TVProviderFragment;

/* loaded from: classes.dex */
public class bxv implements AdapterView.OnItemClickListener, bxu {
    final /* synthetic */ TVProviderFragment a;

    private bxv(TVProviderFragment tVProviderFragment) {
        this.a = tVProviderFragment;
    }

    public /* synthetic */ bxv(TVProviderFragment tVProviderFragment, bxc bxcVar) {
        this(tVProviderFragment);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.handleProviderSelected(adapterView, i, this);
    }

    @Override // defpackage.bxu
    public boolean a() {
        return false;
    }
}
