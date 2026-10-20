package defpackage;

import android.location.Location;
import android.os.Bundle;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes.dex */
public final class ue {
    private Date e;
    private String f;
    private Location h;
    private String j;
    private final HashSet<String> a = new HashSet<>();
    private final Bundle b = new Bundle();
    private final HashMap<Class<? extends Object>, Object> c = new HashMap<>();
    private final HashSet<String> d = new HashSet<>();
    private int g = -1;
    private boolean i = false;
    private int k = -1;

    public void a(int i) {
        this.g = i;
    }

    public void a(Location location) {
        this.h = location;
    }

    public void a(Class<? extends dm> cls, Bundle bundle) {
        this.b.putBundle(cls.getName(), bundle);
    }

    public void a(String str) {
        this.a.add(str);
    }

    public void a(Date date) {
        this.e = date;
    }

    public void a(boolean z) {
        this.k = z ? 1 : 0;
    }

    public void b(String str) {
        this.d.add(str);
    }
}
