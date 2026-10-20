package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.thuuz.gtv.settings.loc.TVProviderFragment;

/* loaded from: classes.dex */
public class bxj implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ TVProviderFragment b;

    public bxj(TVProviderFragment tVProviderFragment, AlertDialog alertDialog) {
        this.b = tVProviderFragment;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.dismiss();
    }
}
