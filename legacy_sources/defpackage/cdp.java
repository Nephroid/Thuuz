package defpackage;

import android.widget.CompoundButton;
import org.jraf.android.backport.switchwidget.SwitchPreference;

/* loaded from: classes.dex */
public class cdp implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SwitchPreference a;

    private cdp(SwitchPreference switchPreference) {
        this.a = switchPreference;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!SwitchPreference.a(this.a, Boolean.valueOf(z))) {
            compoundButton.setChecked(!z);
        } else {
            this.a.a(z);
        }
    }
}
