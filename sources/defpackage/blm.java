package defpackage;

import android.view.View;
import android.widget.ListView;

/* loaded from: classes.dex */
class blm implements View.OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ bll b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public blm(bll bllVar, View view) {
        this.b = bllVar;
        this.a = view;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ListView listView;
        listView = this.b.a.f;
        this.b.a.e(listView.getPositionForView(this.a));
    }
}
