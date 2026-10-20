package defpackage;

import android.widget.CompoundButton;
import android.widget.EditText;
import com.thuuz.gtv.activity.fantasy.FantasyImportSiteActivity;

/* loaded from: classes.dex */
public class bex implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ FantasyImportSiteActivity a;

    public bex(FantasyImportSiteActivity fantasyImportSiteActivity) {
        this.a = fantasyImportSiteActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        EditText editText;
        FantasyImportSiteActivity fantasyImportSiteActivity = this.a;
        editText = this.a.i;
        fantasyImportSiteActivity.a(editText, z);
    }
}
