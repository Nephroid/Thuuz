package defpackage;

import android.content.DialogInterface;
import com.google.android.gms.internal.gv;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class xf implements DialogInterface.OnClickListener {
    final /* synthetic */ xd a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public xf(xd xdVar) {
        this.a = xdVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        gv gvVar;
        gvVar = this.a.a;
        gvVar.b("onStorePictureCanceled", new JSONObject());
    }
}
