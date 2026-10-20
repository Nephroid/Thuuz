package defpackage;

import android.content.DialogInterface;
import com.google.android.gms.internal.gv;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class xb implements DialogInterface.OnClickListener {
    final /* synthetic */ wz a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public xb(wz wzVar) {
        this.a = wzVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        gv gvVar;
        gvVar = this.a.a;
        gvVar.b("onCalendarEventCanceled", new JSONObject());
    }
}
