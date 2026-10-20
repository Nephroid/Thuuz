package defpackage;

import android.view.View;
import com.thuuz.gtv.settings.SettingsFavSportsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bsv implements View.OnClickListener {
    final /* synthetic */ SettingsFavSportsActivity a;

    public bsv(SettingsFavSportsActivity settingsFavSportsActivity) {
        this.a = settingsFavSportsActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            this.a.V();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
