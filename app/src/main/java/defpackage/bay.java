package defpackage;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bay implements View.OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ baw b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bay(baw bawVar, Dialog dialog) {
        this.b = bawVar;
        this.a = dialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AbstractActivity abstractActivity;
        AbstractActivity abstractActivity2;
        AbstractActivity abstractActivity3;
        AbstractActivity abstractActivity4;
        try {
            abstractActivity = this.b.c;
            Intent data = new Intent("android.intent.action.VIEW").setData(Uri.parse(String.format("market://details?id=%s", abstractActivity.getPackageName())));
            abstractActivity2 = this.b.c;
            abstractActivity2.startActivity(data);
            this.b.i = true;
            this.b.e();
            abstractActivity3 = this.b.c;
            App j = abstractActivity3.j();
            abstractActivity4 = this.b.c;
            j.a("rating", "rateit", String.valueOf(abstractActivity4.j().ai()), 0L);
        } catch (Throwable th) {
            this.b.getLogger().log(Level.SEVERE, "problem: ", th);
        }
        this.b.a(this.a);
    }
}
