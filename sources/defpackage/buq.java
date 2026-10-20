package defpackage;

import android.view.View;
import com.thuuz.gtv.settings.SettingsSocialRegisterActivity;

/* loaded from: classes.dex */
public class buq implements View.OnClickListener {
    final /* synthetic */ SettingsSocialRegisterActivity a;

    private buq(SettingsSocialRegisterActivity settingsSocialRegisterActivity) {
        this.a = settingsSocialRegisterActivity;
    }

    public /* synthetic */ buq(SettingsSocialRegisterActivity settingsSocialRegisterActivity, bun bunVar) {
        this(settingsSocialRegisterActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.a.F() == null) {
            this.a.e().severe("cannot get service!");
            return;
        }
        bpw c = this.a.j().aY().c();
        if (c == null) {
            if (this.a.d()) {
                this.a.b("Missing Social Authentication", bbt.pls_try_again_later);
            }
            this.a.e().severe("cannot get cached social user!");
            return;
        }
        new bur(this, c).execute(new Void[0]);
    }
}
