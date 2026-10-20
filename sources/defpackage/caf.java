package defpackage;

import android.content.DialogInterface;
import com.thuuz.gtv.activity.AbstractActivity;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class caf implements DialogInterface.OnCancelListener {
    final /* synthetic */ AbstractActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public caf(AbstractActivity abstractActivity) {
        this.a = abstractActivity;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.a.finish();
    }
}
