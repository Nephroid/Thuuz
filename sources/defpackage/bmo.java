package defpackage;

import android.view.View;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bmo implements View.OnClickListener {
    final /* synthetic */ bmn a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bmo(bmn bmnVar) {
        this.a = bmnVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            view.getParent();
            bmh bmhVar = (bmh) view.getTag();
            if (bmhVar != null) {
                this.a.a(bmhVar.a.intValue(), this.a.a());
            } else {
                this.a.getLogger().severe("highlight click - NULL ViewHolder.");
            }
        } catch (Throwable th) {
            this.a.getLogger().log(Level.SEVERE, "highlight click problem: ", th);
        }
    }
}
