package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.friends.ExpertsActivity;
import com.thuuz.gtv.settings.SettingsAccountActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bhy implements View.OnClickListener {
    final /* synthetic */ ExpertsActivity a;

    private bhy(ExpertsActivity expertsActivity) {
        this.a = expertsActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            if (!this.a.j().i()) {
                this.a.a(SettingsAccountActivity.SOURCE.SOURCE_EXPERTS);
            } else {
                bpc bpcVar = (bpc) view.getTag();
                if (bpcVar == null) {
                    this.a.e().severe("no expert as view tag");
                } else if (bpcVar.e()) {
                    this.a.d(bpcVar.a());
                } else {
                    new bhx(this.a, bpcVar).execute(new Void[0]);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem clicking unfollow: ", th);
        }
    }
}
