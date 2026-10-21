package defpackage;

import android.content.ComponentName;
import android.os.IBinder;
import java.util.HashSet;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class jl {
    final /* synthetic */ jk a;
    private final String b;
    private final jm c = new jm(this);
    private final HashSet<iz<?>.je> d = new HashSet<>();
    private int e = 0;
    private boolean f;
    private IBinder g;
    private ComponentName h;

    public jl(jk jkVar, String str) {
        this.a = jkVar;
        this.b = str;
    }

    public jm a() {
        return this.c;
    }

    /* JADX WARN: Incorrect inner types in method signature: (Liz<*>.je;)V */
    public void a(je jeVar) {
        this.d.add(jeVar);
    }

    public void a(boolean z) {
        this.f = z;
    }

    public String b() {
        return this.b;
    }

    /* JADX WARN: Incorrect inner types in method signature: (Liz<*>.je;)V */
    public void b(je jeVar) {
        this.d.remove(jeVar);
    }

    public boolean c() {
        return this.f;
    }

    /* JADX WARN: Incorrect inner types in method signature: (Liz<*>.je;)Z */
    public boolean c(je jeVar) {
        return this.d.contains(jeVar);
    }

    public int d() {
        return this.e;
    }

    public boolean e() {
        return this.d.isEmpty();
    }

    public IBinder f() {
        return this.g;
    }

    public ComponentName g() {
        return this.h;
    }
}
