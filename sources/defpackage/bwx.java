package defpackage;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.http.Headers;
import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.settings.loc.SettingsEditLocationActivity;
import com.thuuz.gtv.settings.loc.SettingsLocationsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bwx implements AdapterView.OnItemClickListener {
    final /* synthetic */ bpu a;
    final /* synthetic */ AlertDialog b;
    final /* synthetic */ int c;
    final /* synthetic */ SettingsLocationsActivity d;

    public bwx(SettingsLocationsActivity settingsLocationsActivity, bpu bpuVar, AlertDialog alertDialog, int i) {
        this.d = settingsLocationsActivity;
        this.a = bpuVar;
        this.b = alertDialog;
        this.c = i;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        try {
            switch (i) {
                case 0:
                    this.d.j().ax().a(this.a);
                    break;
                case 1:
                    Intent intent = new Intent(this.d.k(), (Class<?>) SettingsEditLocationActivity.class);
                    intent.putExtra(Headers.LOCATION, this.a.a());
                    this.d.startActivityForResult(intent, 291);
                    break;
            }
            this.b.dismiss();
            switch (i) {
                case 0:
                    this.d.c();
                    return;
                case 1:
                default:
                    return;
                case 2:
                    this.d.e(this.c);
                    return;
            }
        } catch (Throwable th) {
            this.d.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
