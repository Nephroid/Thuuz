package defpackage;

import android.content.Intent;
import android.view.View;
import com.thuuz.gtv.activity.nobar.OnboardPage8Activity;
import com.thuuz.gtv.activity.sports.GuideActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class blt implements View.OnClickListener {
    final /* synthetic */ OnboardPage8Activity a;

    public blt(OnboardPage8Activity onboardPage8Activity) {
        this.a = onboardPage8Activity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            this.a.j().m(true);
            Intent intent = new Intent(this.a, (Class<?>) GuideActivity.class);
            intent.addFlags(335544320);
            this.a.startActivity(intent);
            this.a.finish();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem clicking on banner ad: ", th);
        }
    }
}
