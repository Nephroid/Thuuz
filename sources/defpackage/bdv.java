package defpackage;

import android.content.DialogInterface;
import android.widget.AdapterView;
import com.thuuz.gtv.activity.AbstractSettings;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bdv implements DialogInterface.OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ AdapterView.OnItemClickListener b;
    final /* synthetic */ AbstractSettings c;

    public bdv(AbstractSettings abstractSettings, int i, AdapterView.OnItemClickListener onItemClickListener) {
        this.c = abstractSettings;
        this.a = i;
        this.b = onItemClickListener;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            boolean bF = this.c.j().bF();
            this.c.j().h(this.a);
            this.c.j().a("teams", "delete", String.valueOf(this.a), 0L);
            this.c.j().bm();
            this.c.g(bF);
            dialogInterface.cancel();
            if (this.b != null) {
                this.b.onItemClick(null, null, 0, this.a);
            }
        } catch (Throwable th) {
            this.c.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
