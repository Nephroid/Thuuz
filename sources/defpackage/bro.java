package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.settings.SettingsActivity;

/* loaded from: classes.dex */
public class bro implements AdapterView.OnItemClickListener {
    final /* synthetic */ SettingsActivity a;

    public bro(SettingsActivity settingsActivity) {
        this.a = settingsActivity;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.a(view);
    }
}
