package defpackage;

import android.content.DialogInterface;
import com.thuuz.gtv.data.Locations;
import com.thuuz.gtv.settings.loc.SettingsLocationsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bww implements DialogInterface.OnClickListener {
    final /* synthetic */ bpu a;
    final /* synthetic */ int b;
    final /* synthetic */ SettingsLocationsActivity c;

    public bww(SettingsLocationsActivity settingsLocationsActivity, bpu bpuVar, int i) {
        this.c = settingsLocationsActivity;
        this.a = bpuVar;
        this.b = i;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        boolean z = false;
        try {
            Locations ax = this.c.j().ax();
            bpu a = ax.a();
            if (a != null && this.a.a().equals(a.a())) {
                z = true;
            }
            ax.b(this.a.a());
            SettingsLocationsActivity.a(this.c).remove(this.b);
            if (z) {
                if (SettingsLocationsActivity.a(this.c).size() > 0) {
                    ax.a((bpu) SettingsLocationsActivity.a(this.c).get(0));
                } else {
                    ax.a((bpu) null);
                    this.c.j().bm();
                }
            }
            this.c.c();
        } catch (Throwable th) {
            this.c.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
