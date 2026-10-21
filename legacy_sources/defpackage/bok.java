package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.thuuz.gtv.activity.sports.ShareActivity;

/* loaded from: classes.dex */
public class bok implements DialogInterface.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ ShareActivity b;

    public bok(ShareActivity shareActivity, AlertDialog alertDialog) {
        this.b = shareActivity;
        this.a = alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.dismiss();
    }
}
