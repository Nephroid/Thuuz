package defpackage;

import android.view.View;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.sports.GuideActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bnb implements View.OnClickListener {
    final /* synthetic */ GuideActivity a;

    public bnb(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        boz bozVar;
        boz bozVar2;
        boz bozVar3;
        try {
            bozVar = this.a.B;
            if (bozVar != null) {
                bozVar2 = this.a.B;
                String a = bozVar2.a();
                if (a != null) {
                    this.a.e(a, "");
                    App j = this.a.j();
                    bozVar3 = this.a.B;
                    j.a("league_sponsor", "clicked", bozVar3.b(), 0L);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem clicking on banner ad: ", th);
        }
    }
}
