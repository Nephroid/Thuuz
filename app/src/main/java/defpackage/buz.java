package defpackage;

import android.support.v4.os.EnvironmentCompat;
import com.thuuz.gtv.settings.SettingsSportsSelectActivity;

/* loaded from: classes.dex */
public class buz {
    final /* synthetic */ SettingsSportsSelectActivity a;
    private bpr b;
    private bpx c;

    public /* synthetic */ buz(SettingsSportsSelectActivity settingsSportsSelectActivity, bpr bprVar, buw buwVar) {
        this(settingsSportsSelectActivity, bprVar);
    }

    public /* synthetic */ buz(SettingsSportsSelectActivity settingsSportsSelectActivity, bpx bpxVar, buw buwVar) {
        this(settingsSportsSelectActivity, bpxVar);
    }

    private buz(SettingsSportsSelectActivity settingsSportsSelectActivity, bpr bprVar) {
        this.a = settingsSportsSelectActivity;
        this.b = bprVar;
    }

    private buz(SettingsSportsSelectActivity settingsSportsSelectActivity, bpx bpxVar) {
        this.a = settingsSportsSelectActivity;
        this.c = bpxVar;
    }

    public String a() {
        return toString();
    }

    public String toString() {
        if (this.b != null) {
            return this.b.f();
        }
        if (this.c != null) {
            return this.c.c();
        }
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }
}
