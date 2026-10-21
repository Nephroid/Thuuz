package defpackage;

import android.support.v4.widget.ExploreByTouchHelper;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zy
/* loaded from: classes.dex */
public class sw {
    private int b;
    private final Object a = new Object();
    private List<sv> c = new LinkedList();

    public sv a() {
        int i;
        sv svVar;
        sv svVar2 = null;
        synchronized (this.a) {
            if (this.c.size() == 0) {
                adn.a("Queue empty");
                return null;
            }
            if (this.c.size() < 2) {
                sv svVar3 = this.c.get(0);
                svVar3.c();
                return svVar3;
            }
            int i2 = ExploreByTouchHelper.INVALID_ID;
            for (sv svVar4 : this.c) {
                int g = svVar4.g();
                if (g > i2) {
                    svVar = svVar4;
                    i = g;
                } else {
                    i = i2;
                    svVar = svVar2;
                }
                i2 = i;
                svVar2 = svVar;
            }
            this.c.remove(svVar2);
            return svVar2;
        }
    }

    public boolean a(sv svVar) {
        boolean z;
        synchronized (this.a) {
            z = this.c.contains(svVar);
        }
        return z;
    }

    public boolean b(sv svVar) {
        boolean z;
        synchronized (this.a) {
            Iterator<sv> it = this.c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                sv next = it.next();
                if (svVar != next && next.b().equals(svVar.b())) {
                    this.c.remove(svVar);
                    z = true;
                    break;
                }
            }
        }
        return z;
    }

    public void c(sv svVar) {
        synchronized (this.a) {
            if (this.c.size() >= 10) {
                adn.a("Queue is full, current size = " + this.c.size());
                this.c.remove(0);
            }
            int i = this.b;
            this.b = i + 1;
            svVar.a(i);
            this.c.add(svVar);
        }
    }
}
