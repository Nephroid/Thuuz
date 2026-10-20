package defpackage;

import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;

/* loaded from: classes.dex */
class btc implements AdapterView.OnItemClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ int b;
    final /* synthetic */ bsy c;
    final /* synthetic */ AlertDialog d;
    final /* synthetic */ btb e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public btc(btb btbVar, View view, int i, bsy bsyVar, AlertDialog alertDialog) {
        this.e = btbVar;
        this.a = view;
        this.b = i;
        this.c = bsyVar;
        this.d = alertDialog;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        bsz bszVar;
        switch (i) {
            case 0:
                bszVar = this.e.a.j;
                bszVar.onClick(this.a);
                break;
            case 1:
                bsw bswVar = new bsw(this.e.a, this.b, this.c);
                if (this.c.h) {
                    this.e.a.a(this.c.e, bswVar);
                    break;
                } else if (this.c.g) {
                    this.e.a.a(this.c.e, false, this.c.b, (AdapterView.OnItemClickListener) bswVar);
                    break;
                } else if (this.c.f) {
                    this.e.a.a(this.c.e, true, this.c.b, (AdapterView.OnItemClickListener) bswVar);
                    break;
                }
                break;
            default:
                this.e.a.e().severe("unexpected case");
                break;
        }
        this.d.dismiss();
    }
}
