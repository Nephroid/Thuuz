package defpackage;

import android.view.View;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.settings.SettingsMaintAccountActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class buf implements View.OnClickListener {
    final /* synthetic */ SettingsMaintAccountActivity a;

    private buf(SettingsMaintAccountActivity settingsMaintAccountActivity) {
        this.a = settingsMaintAccountActivity;
    }

    public /* synthetic */ buf(SettingsMaintAccountActivity settingsMaintAccountActivity, bud budVar) {
        this(settingsMaintAccountActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        buh buhVar;
        try {
            buhVar = this.a.l;
            buhVar.c(false);
            String aU = this.a.j().aU();
            this.a.j().bl();
            this.a.j().bo();
            this.a.j().a((bph) null);
            IService F = this.a.F();
            if (F != null) {
                F.u();
            }
            this.a.j().q(this.a.j().aY().b());
            new bug(this).execute(new Void[0]);
            this.a.j().a("account", "sign_out", aU, 0L);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
