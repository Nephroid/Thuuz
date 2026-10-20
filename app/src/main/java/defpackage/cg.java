package defpackage;

import android.location.Location;
import com.google.ads.AdRequest;
import java.util.Date;
import java.util.Set;

@Deprecated
/* loaded from: classes.dex */
public final class cg {
    private final Date a;
    private final AdRequest.Gender b;
    private final Set<String> c;
    private final boolean d;
    private final Location e;

    public cg(Date date, AdRequest.Gender gender, Set<String> set, boolean z, Location location) {
        this.a = date;
        this.b = gender;
        this.c = set;
        this.d = z;
        this.e = location;
    }
}
