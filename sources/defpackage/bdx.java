package defpackage;

import android.content.DialogInterface;
import android.widget.AdapterView;
import com.thuuz.gtv.activity.AbstractSettings;

/* loaded from: classes.dex */
public class bdx implements DialogInterface.OnClickListener {
    final /* synthetic */ boolean a;
    final /* synthetic */ int b;
    final /* synthetic */ AdapterView.OnItemClickListener c;
    final /* synthetic */ AbstractSettings d;

    public bdx(AbstractSettings abstractSettings, boolean z, int i, AdapterView.OnItemClickListener onItemClickListener) {
        this.d = abstractSettings;
        this.a = z;
        this.b = i;
        this.c = onItemClickListener;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        boolean bF = this.d.j().bF();
        if (this.a) {
            this.d.j().d(this.b);
        } else {
            this.d.j().b(this.b);
        }
        this.d.j().a("sports", "delete", String.valueOf(this.b), 0L);
        this.d.j().bm();
        this.d.g(bF);
        dialogInterface.cancel();
        if (this.c != null) {
            this.c.onItemClick(null, null, 0, this.b);
        }
    }
}
