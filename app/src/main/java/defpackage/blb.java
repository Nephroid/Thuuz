package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.nobar.OnboardPage2Activity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class blb implements View.OnClickListener {
    final /* synthetic */ OnboardPage2Activity a;

    public blb(OnboardPage2Activity onboardPage2Activity) {
        this.a = onboardPage2Activity;
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
