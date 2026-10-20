package defpackage;

import android.view.View;
import com.thuuz.gtv.settings.SettingsActivity;

/* loaded from: classes.dex */
public class brp implements View.OnFocusChangeListener {
    final /* synthetic */ SettingsActivity a;

    public brp(SettingsActivity settingsActivity) {
        this.a = settingsActivity;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        this.a.e().info("list focused: " + z);
    }
}
