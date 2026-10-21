package defpackage;

import android.view.View;
import android.widget.ListView;

/* loaded from: classes.dex */
class blr implements View.OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ blq b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public blr(blq blqVar, View view) {
        this.b = blqVar;
        this.a = view;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ListView listView;
        listView = this.b.b.j;
        this.b.b.e(listView.getPositionForView(this.a));
    }
}
