package defpackage;

import android.content.DialogInterface;
import com.thuuz.gtv.settings.SettingsTeamDetailActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
class bvh implements DialogInterface.OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ bve b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bvh(bve bveVar, int i) {
        this.b = bveVar;
        this.a = i;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            bpt a = this.b.a.j().a(this.a);
            a.a(false);
            this.b.a.j().a(a);
            SettingsTeamDetailActivity.a(this.b.a);
            dialogInterface.cancel();
        } catch (Throwable th) {
            this.b.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
