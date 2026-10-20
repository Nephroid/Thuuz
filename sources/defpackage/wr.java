package defpackage;

import android.location.Location;
import java.util.Date;
import java.util.Set;

@zy
/* loaded from: classes.dex */
public final class wr implements dl {
    private final Date a;
    private final int b;
    private final Set<String> c;
    private final boolean d;
    private final Location e;
    private final int f;

    public wr(Date date, int i, Set<String> set, Location location, boolean z, int i2) {
        this.a = date;
        this.b = i;
        this.c = set;
        this.e = location;
        this.d = z;
        this.f = i2;
    }

    @Override // defpackage.dl
    public Date a() {
        return this.a;
    }

    @Override // defpackage.dl
    public int b() {
        return this.b;
    }

    @Override // defpackage.dl
    public Set<String> c() {
        return this.c;
    }

    @Override // defpackage.dl
    public Location d() {
        return this.e;
    }

    @Override // defpackage.dl
    public boolean e() {
        return this.d;
    }
}
