package defpackage;

import android.app.Dialog;
import android.view.View;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bba implements View.OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ baw b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bba(baw bawVar, Dialog dialog) {
        this.b = bawVar;
        this.a = dialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AbstractActivity abstractActivity;
        AbstractActivity abstractActivity2;
        try {
            this.b.j = true;
            this.b.e();
            abstractActivity = this.b.c;
            App j = abstractActivity.j();
            abstractActivity2 = this.b.c;
            j.a("rating", "never", String.valueOf(abstractActivity2.j().ai()), 0L);
        } catch (Throwable th) {
            this.b.getLogger().log(Level.SEVERE, "problem: ", th);
        }
        this.b.a(this.a);
    }
}
