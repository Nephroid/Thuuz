package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.settings.SettingsAlertsActivity;

/* loaded from: classes.dex */
public class bsf implements AdapterView.OnItemClickListener {
    final /* synthetic */ SettingsAlertsActivity a;

    private bsf(SettingsAlertsActivity settingsAlertsActivity) {
        this.a = settingsAlertsActivity;
    }

    public /* synthetic */ bsf(SettingsAlertsActivity settingsAlertsActivity, bry bryVar) {
        this(settingsAlertsActivity);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.e(i);
    }
}
