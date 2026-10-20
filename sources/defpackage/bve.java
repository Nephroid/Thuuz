package defpackage;

import android.app.AlertDialog;
import android.widget.CompoundButton;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.settings.SettingsTeamDetailActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bve implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SettingsTeamDetailActivity a;

    public bve(SettingsTeamDetailActivity settingsTeamDetailActivity) {
        this.a = settingsTeamDetailActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        bpz a;
        bpt a2;
        bps d;
        try {
            IService F = this.a.F();
            if (!z && F != null && (a = F.a(Integer.valueOf(this.a.f))) != null) {
                int d2 = a.d();
                if (this.a.j().r().contains(Integer.valueOf(d2))) {
                    bpt c = this.a.j().c(d2);
                    if (c != null && c.e() && (d = F.d(d2)) != null) {
                        String e = d.e();
                        if (e == null) {
                            e = d.b();
                        }
                        String format = String.format("The %s still has alerts enabled.", e);
                        AlertDialog.Builder builder = new AlertDialog.Builder(this.a.k());
                        builder.setTitle("League Group still enabled");
                        builder.setMessage(format).setCancelable(true).setPositiveButton("Ok", new bvg(this)).setNegativeButton("Disable Them Too", new bvf(this, d2));
                        AlertDialog create = builder.create();
                        this.a.a(create);
                        if (!this.a.isDestroyed()) {
                            create.show();
                        }
                    }
                } else {
                    int e2 = a.e();
                    if (this.a.j().o().contains(Integer.valueOf(e2)) && (a2 = this.a.j().a(e2)) != null && a2.e()) {
                        bpr c2 = F.c(e2);
                        String h = c2.h();
                        if (h == null) {
                            h = c2.f();
                        }
                        String format2 = String.format("The %s still has alerts enabled.", h);
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(this.a.k());
                        builder2.setTitle("League still enabled");
                        builder2.setMessage(format2).setCancelable(true).setPositiveButton("Ok", new bvi(this)).setNegativeButton("Disable Them Too", new bvh(this, e2));
                        AlertDialog create2 = builder2.create();
                        this.a.a(create2);
                        if (!this.a.isDestroyed()) {
                            create2.show();
                        }
                    }
                }
            }
            this.a.D.a(z);
            this.a.o.setVisibility(z ? 0 : 8);
            SettingsTeamDetailActivity.a(this.a);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
