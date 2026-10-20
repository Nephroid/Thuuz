package defpackage;

import com.google.ads.AdRequest;
import com.google.android.gms.internal.av;
import com.google.android.gms.internal.ay;
import java.util.Date;
import java.util.HashSet;

@zy
/* loaded from: classes.dex */
public final class wy {
    public static cf a(ay ayVar) {
        cf[] cfVarArr = {cf.a, cf.b, cf.c, cf.d, cf.e, cf.f};
        for (int i = 0; i < cfVarArr.length; i++) {
            if (cfVarArr[i].a() == ayVar.f && cfVarArr[i].b() == ayVar.c) {
                return cfVarArr[i];
            }
        }
        return new cf(dd.a(ayVar.f, ayVar.c, ayVar.b));
    }

    public static cg a(av avVar) {
        return new cg(new Date(avVar.b), a(avVar.d), avVar.e != null ? new HashSet(avVar.e) : null, avVar.f, avVar.k);
    }

    public static AdRequest.Gender a(int i) {
        switch (i) {
            case 1:
                return AdRequest.Gender.MALE;
            case 2:
                return AdRequest.Gender.FEMALE;
            default:
                return AdRequest.Gender.UNKNOWN;
        }
    }
}
