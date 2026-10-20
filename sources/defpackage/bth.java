package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.settings.SettingsFriendAlertsActivity;

/* loaded from: classes.dex */
public class bth implements AdapterView.OnItemClickListener {
    final /* synthetic */ SettingsFriendAlertsActivity a;

    private bth(SettingsFriendAlertsActivity settingsFriendAlertsActivity) {
        this.a = settingsFriendAlertsActivity;
    }

    public /* synthetic */ bth(SettingsFriendAlertsActivity settingsFriendAlertsActivity, bte bteVar) {
        this(settingsFriendAlertsActivity);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.e(i);
    }
}
