package defpackage;

import com.thuuz.gtv.IService;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class cbe extends bzy {
    Timer a;
    private Set<Integer> b;
    private WeakReference<IService> c;

    private synchronized void c() {
        IService iService;
        if (this.b.size() != 0 && (iService = this.c.get()) != null) {
            Iterator it = new HashSet(this.b).iterator();
            while (it.hasNext()) {
                Integer num = (Integer) it.next();
                iService.l();
                this.b.remove(num);
            }
        }
    }

    public synchronized void a() {
        try {
            this.a.cancel();
            if (this.b.size() > 0) {
                c();
                this.b.clear();
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    public synchronized boolean b() {
        return this.b.size() > 0;
    }
}
