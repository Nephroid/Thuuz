package defpackage;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class cbb<E> extends bzy {
    private long a;
    private Map<String, cbb<E>.cbc> b = Collections.synchronizedMap(new HashMap());

    public cbb(long j) {
        this.a = 60000L;
        this.a = j;
    }

    public E a(String str) {
        b();
        if (str == null) {
            return null;
        }
        cbc cbcVar = this.b.get(d(str));
        if (cbcVar != null) {
            return cbcVar.b;
        }
        return null;
    }

    private String d(String str) {
        String[] split = str.split("\\?");
        if (split.length > 1) {
            return split[0];
        }
        return str;
    }

    private void b() {
        try {
            Date date = new Date(System.currentTimeMillis() - this.a);
            for (String str : new HashSet(this.b.keySet())) {
                cbc cbcVar = this.b.get(str);
                if (cbcVar != null && cbcVar.a.before(date)) {
                    this.b.remove(str);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void a(String str, E e) {
        b();
        this.b.put(d(str), new cbc(this, new Date(), e));
    }

    public void b(String str) {
        b();
        this.b.remove(d(str));
    }

    public E c(String str) {
        try {
            Date date = new Date(System.currentTimeMillis() - this.a);
            cbc cbcVar = this.b.get(d(str));
            if (cbcVar != null && cbcVar.a.before(date)) {
                return cbcVar.b;
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
        return null;
    }

    public void a() {
        this.b.clear();
    }
}
