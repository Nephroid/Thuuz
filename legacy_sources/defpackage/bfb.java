package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import com.thuuz.gtv.activity.fantasy.FantasyImportSiteActivity;

/* loaded from: classes.dex */
public class bfb implements TextWatcher {
    final /* synthetic */ FantasyImportSiteActivity a;

    private bfb(FantasyImportSiteActivity fantasyImportSiteActivity) {
        this.a = fantasyImportSiteActivity;
    }

    public /* synthetic */ bfb(FantasyImportSiteActivity fantasyImportSiteActivity, bew bewVar) {
        this(fantasyImportSiteActivity);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        boolean z;
        Button button;
        boolean aF;
        z = this.a.k;
        if (!z) {
            button = this.a.j;
            aF = this.a.aF();
            button.setEnabled(aF);
        }
    }
}
