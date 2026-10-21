package defpackage;

import android.app.Dialog;
import android.view.View;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bbe implements View.OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ bbb b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bbe(bbb bbbVar, Dialog dialog) {
        this.b = bbbVar;
        this.a = dialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AbstractActivity abstractActivity;
        AbstractActivity abstractActivity2;
        try {
            this.b.g = true;
            this.b.e();
            abstractActivity = this.b.b;
            App j = abstractActivity.j();
            abstractActivity2 = this.b.b;
            j.a("invitereminder", "never", String.valueOf(abstractActivity2.j().ai()), 0L);
        } catch (Throwable th) {
            this.b.getLogger().log(Level.SEVERE, "problem: ", th);
        }
        this.b.a(this.a);
    }
}
