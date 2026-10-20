package defpackage;

import android.content.DialogInterface;
import com.thuuz.gtv.activity.AbstractSettings;

/* loaded from: classes.dex */
public class bdu implements DialogInterface.OnClickListener {
    final /* synthetic */ AbstractSettings a;

    public bdu(AbstractSettings abstractSettings) {
        this.a = abstractSettings;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
