package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class awr extends awu implements Iterable<awu> {
    private final List<awu> a = new ArrayList();

    public void a(awu awuVar) {
        if (awuVar == null) {
            awuVar = awv.a;
        }
        this.a.add(awuVar);
    }

    @Override // java.lang.Iterable
    public Iterator<awu> iterator() {
        return this.a.iterator();
    }

    @Override // defpackage.awu
    public Number a() {
        if (this.a.size() == 1) {
            return this.a.get(0).a();
        }
        throw new IllegalStateException();
    }

    @Override // defpackage.awu
    public String b() {
        if (this.a.size() == 1) {
            return this.a.get(0).b();
        }
        throw new IllegalStateException();
    }

    @Override // defpackage.awu
    public boolean c() {
        if (this.a.size() == 1) {
            return this.a.get(0).c();
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof awr) && ((awr) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
