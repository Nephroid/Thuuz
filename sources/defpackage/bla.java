package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.nobar.OnboardPage1Activity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bla implements View.OnClickListener {
    final /* synthetic */ OnboardPage1Activity a;

    public bla(OnboardPage1Activity onboardPage1Activity) {
        this.a = onboardPage1Activity;
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
