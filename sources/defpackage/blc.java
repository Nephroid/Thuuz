package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.nobar.OnboardPage3Activity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class blc implements View.OnClickListener {
    final /* synthetic */ OnboardPage3Activity a;

    public blc(OnboardPage3Activity onboardPage3Activity) {
        this.a = onboardPage3Activity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            if (this.a.b("android.permission.ACCESS_COARSE_LOCATION", false)) {
                this.a.aG();
            } else {
                this.a.aH();
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem clicking on banner ad: ", th);
        }
    }
}
