package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.nobar.OnboardPage7LeaguesActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class blo implements View.OnClickListener {
    final /* synthetic */ OnboardPage7LeaguesActivity a;

    public blo(OnboardPage7LeaguesActivity onboardPage7LeaguesActivity) {
        this.a = onboardPage7LeaguesActivity;
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
