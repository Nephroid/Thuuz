package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.nobar.OnboardPage3aLocationActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bld implements View.OnClickListener {
    final /* synthetic */ OnboardPage3aLocationActivity a;

    public bld(OnboardPage3aLocationActivity onboardPage3aLocationActivity) {
        this.a = onboardPage3aLocationActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            bqy.a(new ble(this));
            if (!this.a.b("android.permission.ACCESS_COARSE_LOCATION", true)) {
                this.a.aG();
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem clicking on banner ad: ", th);
        }
    }
}
