package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.fantasy.FantasyImportSiteActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bew implements View.OnClickListener {
    final /* synthetic */ FantasyImportSiteActivity a;

    public bew(FantasyImportSiteActivity fantasyImportSiteActivity) {
        this.a = fantasyImportSiteActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            if (this.a.F() != null) {
                this.a.f(false);
                new bey(this.a).execute(new Void[0]);
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
