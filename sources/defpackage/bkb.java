package defpackage;

import android.view.View;
import java.util.ArrayList;

/* loaded from: classes.dex */
class bkb implements View.OnClickListener {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ bjy b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bkb(bjy bjyVar, ArrayList arrayList) {
        this.b = bjyVar;
        this.a = arrayList;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.b.a.handleWatchNow(this.a, view);
    }
}
