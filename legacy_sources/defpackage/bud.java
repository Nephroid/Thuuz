package defpackage;

import com.thuuz.gtv.settings.SettingsMaintAccountActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bud implements Runnable {
    final /* synthetic */ SettingsMaintAccountActivity a;

    public bud(SettingsMaintAccountActivity settingsMaintAccountActivity) {
        this.a = settingsMaintAccountActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.aF();
            this.a.aG();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
