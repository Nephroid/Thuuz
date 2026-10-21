package defpackage;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.JsonIOException;
import com.google.gson.LongSerializationPolicy;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class awi {
    final aws a;
    final awy b;
    private final ThreadLocal<Map<bau<?>, awo<?>>> c;
    private final Map<bau<?>, axd<?>> d;
    private final List<axe> e;
    private final axk f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final boolean j;

    public awi() {
        this(axx.a, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public awi(axx axxVar, awh awhVar, Map<Type, awq<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, LongSerializationPolicy longSerializationPolicy, List<axe> list) {
        this.c = new ThreadLocal<>();
        this.d = Collections.synchronizedMap(new HashMap());
        this.a = new awj(this);
        this.b = new awk(this);
        this.f = new axk(map);
        this.g = z;
        this.i = z3;
        this.h = z4;
        this.j = z5;
        ArrayList arrayList = new ArrayList();
        arrayList.add(azm.Q);
        arrayList.add(azb.a);
        arrayList.add(axxVar);
        arrayList.addAll(list);
        arrayList.add(azm.x);
        arrayList.add(azm.m);
        arrayList.add(azm.g);
        arrayList.add(azm.i);
        arrayList.add(azm.k);
        arrayList.add(azm.a(Long.TYPE, Long.class, a(longSerializationPolicy)));
        arrayList.add(azm.a(Double.TYPE, Double.class, a(z6)));
        arrayList.add(azm.a(Float.TYPE, Float.class, b(z6)));
        arrayList.add(azm.r);
        arrayList.add(azm.t);
        arrayList.add(azm.z);
        arrayList.add(azm.B);
        arrayList.add(azm.a(BigDecimal.class, azm.v));
        arrayList.add(azm.a(BigInteger.class, azm.w));
        arrayList.add(azm.D);
        arrayList.add(azm.F);
        arrayList.add(azm.J);
        arrayList.add(azm.O);
        arrayList.add(azm.H);
        arrayList.add(azm.d);
        arrayList.add(ayv.a);
        arrayList.add(azm.M);
        arrayList.add(azj.a);
        arrayList.add(azh.a);
        arrayList.add(azm.K);
        arrayList.add(ayr.a);
        arrayList.add(azm.R);
        arrayList.add(azm.b);
        arrayList.add(new ayt(this.f));
        arrayList.add(new ayz(this.f, z2));
        arrayList.add(new azd(this.f, awhVar, axxVar));
        this.e = Collections.unmodifiableList(arrayList);
    }

    private axd<Number> a(boolean z) {
        return z ? azm.p : new awl(this);
    }

    private axd<Number> b(boolean z) {
        return z ? azm.o : new awm(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private axd<Number> a(LongSerializationPolicy longSerializationPolicy) {
        return longSerializationPolicy == LongSerializationPolicy.DEFAULT ? azm.n : new awn(this);
    }

    public <T> axd<T> a(bau<T> bauVar) {
        Map map;
        axd<T> axdVar = (axd) this.d.get(bauVar);
        if (axdVar == null) {
            Map<bau<?>, awo<?>> map2 = this.c.get();
            boolean z = false;
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                this.c.set(hashMap);
                map = hashMap;
                z = true;
            } else {
                map = map2;
            }
            axdVar = (awo) map.get(bauVar);
            if (axdVar == null) {
                try {
                    awo awoVar = new awo();
                    map.put(bauVar, awoVar);
                    Iterator<axe> it = this.e.iterator();
                    while (it.hasNext()) {
                        axdVar = it.next().a(this, bauVar);
                        if (axdVar != null) {
                            awoVar.a((axd) axdVar);
                            this.d.put(bauVar, axdVar);
                            map.remove(bauVar);
                            if (z) {
                                this.c.remove();
                            }
                        }
                    }
                    throw new IllegalArgumentException("GSON cannot handle " + bauVar);
                } catch (Throwable th) {
                    map.remove(bauVar);
                    if (z) {
                        this.c.remove();
                    }
                    throw th;
                }
            }
        }
        return axdVar;
    }

    public <T> axd<T> a(axe axeVar, bau<T> bauVar) {
        boolean z = false;
        for (axe axeVar2 : this.e) {
            if (!z) {
                if (axeVar2 == axeVar) {
                    z = true;
                }
            } else {
                axd<T> a = axeVar2.a(this, bauVar);
                if (a != null) {
                    return a;
                }
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + bauVar);
    }

    public <T> axd<T> a(Class<T> cls) {
        return a((bau) bau.b(cls));
    }

    public awu a(Object obj) {
        return obj == null ? awv.a : a(obj, obj.getClass());
    }

    public awu a(Object obj, Type type) {
        ayx ayxVar = new ayx();
        a(obj, type, ayxVar);
        return ayxVar.a();
    }

    public String b(Object obj) {
        return obj == null ? a((awu) awv.a) : b(obj, obj.getClass());
    }

    public String b(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        a(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void a(Object obj, Type type, Appendable appendable) {
        try {
            a(obj, type, a(ayi.a(appendable)));
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    public void a(Object obj, Type type, bav bavVar) {
        axd a = a((bau) bau.a(type));
        boolean g = bavVar.g();
        bavVar.b(true);
        boolean h = bavVar.h();
        bavVar.c(this.h);
        boolean i = bavVar.i();
        bavVar.d(this.g);
        try {
            try {
                a.a(bavVar, obj);
            } catch (IOException e) {
                throw new JsonIOException(e);
            }
        } finally {
            bavVar.b(g);
            bavVar.c(h);
            bavVar.d(i);
        }
    }

    public String a(awu awuVar) {
        StringWriter stringWriter = new StringWriter();
        a(awuVar, stringWriter);
        return stringWriter.toString();
    }

    public void a(awu awuVar, Appendable appendable) {
        try {
            a(awuVar, a(ayi.a(appendable)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private bav a(Writer writer) {
        if (this.i) {
            writer.write(")]}'\n");
        }
        bav bavVar = new bav(writer);
        if (this.j) {
            bavVar.c("  ");
        }
        bavVar.d(this.g);
        return bavVar;
    }

    public void a(awu awuVar, bav bavVar) {
        boolean g = bavVar.g();
        bavVar.b(true);
        boolean h = bavVar.h();
        bavVar.c(this.h);
        boolean i = bavVar.i();
        bavVar.d(this.g);
        try {
            try {
                ayi.a(awuVar, bavVar);
            } catch (IOException e) {
                throw new JsonIOException(e);
            }
        } finally {
            bavVar.b(g);
            bavVar.c(h);
            bavVar.d(i);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.g + "factories:" + this.e + ",instanceCreators:" + this.f + "}";
    }
}
