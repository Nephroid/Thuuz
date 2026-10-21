package defpackage;

import android.app.Dialog;
import android.view.View;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bbd implements View.OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ bbb b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bbd(bbb bbbVar, Dialog dialog) {
        this.b = bbbVar;
        this.a = dialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AbstractActivity abstractActivity;
        AbstractActivity abstractActivity2;
        AbstractActivity abstractActivity3;
        try {
            abstractActivity = this.b.b;
            abstractActivity.ah();
            this.b.f = true;
            this.b.e();
            abstractActivity2 = this.b.b;
            App j = abstractActivity2.j();
            abstractActivity3 = this.b.b;
            j.a("invitereminder", "invite", String.valueOf(abstractActivity3.j().ai()), 0L);
        } catch (Throwable th) {
            this.b.getLogger().log(Level.SEVERE, "problem: ", th);
        }
        this.b.a(this.a);
    }
}
