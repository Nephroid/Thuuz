package defpackage;

import android.content.Intent;
import android.view.View;
import com.thuuz.gtv.activity.nobar.OnboardPage8Activity;
import com.thuuz.gtv.settings.AbstractSettingsOnboardingActivity;

/* loaded from: classes.dex */
public class brb implements View.OnClickListener {
    final /* synthetic */ AbstractSettingsOnboardingActivity a;

    public brb(AbstractSettingsOnboardingActivity abstractSettingsOnboardingActivity) {
        this.a = abstractSettingsOnboardingActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.startActivity(new Intent(this.a, (Class<?>) OnboardPage8Activity.class));
    }
}
