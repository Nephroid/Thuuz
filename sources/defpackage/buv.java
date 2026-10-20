package defpackage;

import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.settings.SettingsSoundsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class buv implements AdapterView.OnItemSelectedListener {
    final /* synthetic */ SettingsSoundsActivity a;

    private buv(SettingsSoundsActivity settingsSoundsActivity) {
        this.a = settingsSoundsActivity;
    }

    public /* synthetic */ buv(SettingsSoundsActivity settingsSoundsActivity, bus busVar) {
        this(settingsSoundsActivity);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        Vibrator vibrator;
        try {
            this.a.e().info("selected vib id/position: " + j + "/" + i);
            SettingsSoundsActivity.VIB_TYPE a = SettingsSoundsActivity.VIB_TYPE.a(i);
            this.a.j().a(a);
            if (a != SettingsSoundsActivity.VIB_TYPE.NO_VIB && a != SettingsSoundsActivity.VIB_TYPE.USE_DEFAULT && (vibrator = (Vibrator) this.a.getSystemService("vibrator")) != null && a.b() != null) {
                vibrator.vibrate(a.b(), -1);
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem echoing vib setting: ", th);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
