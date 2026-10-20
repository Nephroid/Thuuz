package defpackage;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.LongSerializationPolicy;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class awp {
    private boolean g;
    private String h;
    private boolean k;
    private boolean l;
    private boolean n;
    private boolean o;
    private axx a = axx.a;
    private LongSerializationPolicy b = LongSerializationPolicy.DEFAULT;
    private awh c = FieldNamingPolicy.IDENTITY;
    private final Map<Type, awq<?>> d = new HashMap();
    private final List<axe> e = new ArrayList();
    private final List<axe> f = new ArrayList();
    private int i = 2;
    private int j = 2;
    private boolean m = true;

    public awp a() {
        this.a = this.a.b();
        return this;
    }

    public awp b() {
        this.m = false;
        return this;
    }

    public awp a(Type type, Object obj) {
        axj.a((obj instanceof awz) || (obj instanceof awt) || (obj instanceof awq) || (obj instanceof axd));
        if (obj instanceof awq) {
            this.d.put(type, (awq) obj);
        }
        if ((obj instanceof awz) || (obj instanceof awt)) {
            this.e.add(axa.b(bau.a(type), obj));
        }
        if (obj instanceof axd) {
            this.e.add(azm.a(bau.a(type), (axd) obj));
        }
        return this;
    }

    public awp a(Class<?> cls, Object obj) {
        axj.a((obj instanceof awz) || (obj instanceof awt) || (obj instanceof axd));
        if ((obj instanceof awt) || (obj instanceof awz)) {
            this.f.add(0, axa.a(cls, obj));
        }
        if (obj instanceof axd) {
            this.e.add(azm.b(cls, (axd) obj));
        }
        return this;
    }

    public awi c() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.e);
        Collections.reverse(arrayList);
        arrayList.addAll(this.f);
        a(this.h, this.i, this.j, arrayList);
        return new awi(this.a, this.c, this.d, this.g, this.k, this.o, this.m, this.n, this.l, this.b, arrayList);
    }

    private void a(String str, int i, int i2, List<axe> list) {
        awe aweVar;
        if (str != null && !"".equals(str.trim())) {
            aweVar = new awe(str);
        } else if (i != 2 && i2 != 2) {
            aweVar = new awe(i, i2);
        } else {
            return;
        }
        list.add(axa.a((bau<?>) bau.b(Date.class), aweVar));
        list.add(axa.a((bau<?>) bau.b(Timestamp.class), aweVar));
        list.add(axa.a((bau<?>) bau.b(java.sql.Date.class), aweVar));
    }
}
