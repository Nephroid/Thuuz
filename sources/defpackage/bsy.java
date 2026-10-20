package defpackage;

import com.thuuz.gtv.settings.SettingsFavSportsActivity;

/* loaded from: classes.dex */
public class bsy implements Comparable<bsy> {
    public String a;
    public final String b;
    public String c;
    public final boolean d;
    public final int e;
    public boolean f;
    public boolean g;
    public boolean h;
    final /* synthetic */ SettingsFavSportsActivity i;

    private bsy(SettingsFavSportsActivity settingsFavSportsActivity, String str, String str2, String str3, boolean z, int i) {
        this.i = settingsFavSportsActivity;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = z;
        this.e = i;
    }

    public bsy(SettingsFavSportsActivity settingsFavSportsActivity, String str, boolean z, bps bpsVar) {
        this(settingsFavSportsActivity, str, bpsVar.d().f() + " " + bpsVar.b(), "", z, bpsVar.c());
        this.g = false;
        this.f = true;
        this.h = false;
    }

    public bsy(SettingsFavSportsActivity settingsFavSportsActivity, String str, boolean z, bpr bprVar) {
        this(settingsFavSportsActivity, str, bprVar.f(), "", z, bprVar.g());
        this.g = true;
        this.f = false;
        this.h = false;
    }

    public bsy(SettingsFavSportsActivity settingsFavSportsActivity, String str, boolean z, bpz bpzVar) {
        this(settingsFavSportsActivity, str, bpzVar.b(settingsFavSportsActivity.F()), "", z, bpzVar.b());
        this.g = false;
        this.f = false;
        this.h = true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bsy bsyVar) {
        if (this.b == null || bsyVar.b == null) {
            return 0;
        }
        if (this.b.compareTo(bsyVar.b) == 0) {
            if (this.c == null || bsyVar.c == null) {
                return 0;
            }
            return this.c.compareTo(bsyVar.c);
        }
        return this.b.compareTo(bsyVar.b);
    }

    public String toString() {
        return "FavoriteSports(): " + this.b + " - " + this.c;
    }
}
