package defpackage;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.thuuz.gtv.settings.SettingsSoundsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class buu implements AdapterView.OnItemSelectedListener {
    final /* synthetic */ SettingsSoundsActivity a;

    private buu(SettingsSoundsActivity settingsSoundsActivity) {
        this.a = settingsSoundsActivity;
    }

    public /* synthetic */ buu(SettingsSoundsActivity settingsSoundsActivity, bus busVar) {
        this(settingsSoundsActivity);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        try {
            this.a.e().info("selected toast id/position: " + j + "/" + i);
            SettingsSoundsActivity.TOAST_TYPE a = SettingsSoundsActivity.TOAST_TYPE.a(i);
            this.a.j().a(a);
            String str = "Alert duration: " + a.name().toLowerCase().replace("_", " ");
            Toast.makeText(this.a.k(), str, a.a()).show();
            if (a == SettingsSoundsActivity.TOAST_TYPE.DOUBLE_LONG) {
                Toast.makeText(this.a.k(), str, a.a()).show();
            }
            if (a == SettingsSoundsActivity.TOAST_TYPE.TRIPLE_LONG) {
                Toast.makeText(this.a.k(), str, a.a()).show();
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem setting toast duration: ", th);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
