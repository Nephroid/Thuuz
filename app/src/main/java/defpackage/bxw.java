package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.settings.loc.TVProviderFragment;

/* loaded from: classes.dex */
public class bxw implements AdapterView.OnItemSelectedListener, bxu {
    final /* synthetic */ TVProviderFragment a;

    private bxw(TVProviderFragment tVProviderFragment) {
        this.a = tVProviderFragment;
    }

    public /* synthetic */ bxw(TVProviderFragment tVProviderFragment, bxc bxcVar) {
        this(tVProviderFragment);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        TVProviderFragment.access$2400(this.a, adapterView, i, this);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override // defpackage.bxu
    public boolean a() {
        return true;
    }
}
