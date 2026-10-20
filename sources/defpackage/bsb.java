package defpackage;

import com.thuuz.gtv.settings.SettingsAlertsActivity;

/* loaded from: classes.dex */
class bsb implements Runnable {
    final /* synthetic */ bsa a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bsb(bsa bsaVar) {
        this.a = bsaVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        SettingsAlertsActivity settingsAlertsActivity;
        if (this.a.a.f != null) {
            this.a.a.f.dismiss();
        }
        settingsAlertsActivity = this.a.a.k;
        cad.b(settingsAlertsActivity, "Error Updating Registration ID", "Please try again later.");
        this.a.a.l = null;
    }
}
