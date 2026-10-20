package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.nobar.OnboardPage4TvActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class blg implements View.OnClickListener {
    final /* synthetic */ OnboardPage4TvActivity a;

    public blg(OnboardPage4TvActivity onboardPage4TvActivity) {
        this.a = onboardPage4TvActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            this.a.aF();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem clicking on banner ad: ", th);
        }
    }
}
