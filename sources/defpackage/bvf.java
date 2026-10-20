package defpackage;

import android.content.DialogInterface;
import com.thuuz.gtv.settings.SettingsTeamDetailActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
class bvf implements DialogInterface.OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ bve b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bvf(bve bveVar, int i) {
        this.b = bveVar;
        this.a = i;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            bpt c = this.b.a.j().c(this.a);
            c.a(false);
            this.b.a.j().b(c);
            SettingsTeamDetailActivity.a(this.b.a);
            dialogInterface.cancel();
        } catch (Throwable th) {
            this.b.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
