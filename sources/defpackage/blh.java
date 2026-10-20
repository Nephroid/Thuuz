package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.nobar.OnboardPage6TeamsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class blh implements View.OnClickListener {
    final /* synthetic */ OnboardPage6TeamsActivity a;

    public blh(OnboardPage6TeamsActivity onboardPage6TeamsActivity) {
        this.a = onboardPage6TeamsActivity;
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
