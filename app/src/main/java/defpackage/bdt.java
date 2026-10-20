package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.thuuz.gtv.activity.AbstractSettings;

/* loaded from: classes.dex */
public class bdt implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ AbstractSettings b;

    public bdt(AbstractSettings abstractSettings, AlertDialog alertDialog) {
        this.b = abstractSettings;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        boolean bF = this.b.j().bF();
        this.b.j().i(true);
        this.b.j().bm();
        this.b.g(bF);
        this.b.d("Alerts ON", "Global alert preferences have been turned ON.");
        this.a.dismiss();
    }
}
