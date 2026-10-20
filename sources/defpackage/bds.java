package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.thuuz.gtv.activity.AbstractSettings;

/* loaded from: classes.dex */
public class bds implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ AbstractSettings b;

    public bds(AbstractSettings abstractSettings, AlertDialog alertDialog) {
        this.b = abstractSettings;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.dismiss();
    }
}
