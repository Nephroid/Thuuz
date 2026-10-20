package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.nobar.OnboardPage3aLocationActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class blf implements View.OnClickListener {
    final /* synthetic */ OnboardPage3aLocationActivity a;

    public blf(OnboardPage3aLocationActivity onboardPage3aLocationActivity) {
        this.a = onboardPage3aLocationActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            this.a.aG();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem clicking on banner ad: ", th);
        }
    }
}
