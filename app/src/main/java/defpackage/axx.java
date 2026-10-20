package defpackage;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class axx implements axe, Cloneable {
    public static final axx a = new axx();
    private boolean e;
    private double b = -1.0d;
    private int c = 136;
    private boolean d = true;
    private List<awf> f = Collections.emptyList();
    private List<awf> g = Collections.emptyList();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public axx clone() {
        try {
            return (axx) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public axx b() {
        axx clone = clone();
        clone.e = true;
        return clone;
    }

    @Override // defpackage.axe
    public <T> axd<T> a(awi awiVar, bau<T> bauVar) {
        Class<? super T> a2 = bauVar.a();
        boolean a3 = a((Class<?>) a2, true);
        boolean a4 = a((Class<?>) a2, false);
        if (a3 || a4) {
            return new axy(this, a4, a3, awiVar, bauVar);
        }
        return null;
    }

    public boolean a(Field field, boolean z) {
        axf axfVar;
        if ((this.c & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.b == -1.0d || a((axh) field.getAnnotation(axh.class), (axi) field.getAnnotation(axi.class))) && !field.isSynthetic()) {
            if (this.e && ((axfVar = (axf) field.getAnnotation(axf.class)) == null || (!z ? axfVar.b() : axfVar.a()))) {
                return true;
            }
            if ((this.d || !b(field.getType())) && !a(field.getType())) {
                List<awf> list = z ? this.f : this.g;
                if (!list.isEmpty()) {
                    awg awgVar = new awg(field);
                    Iterator<awf> it = list.iterator();
                    while (it.hasNext()) {
                        if (it.next().a(awgVar)) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return true;
        }
        return true;
    }

    public boolean a(Class<?> cls, boolean z) {
        if (this.b != -1.0d && !a((axh) cls.getAnnotation(axh.class), (axi) cls.getAnnotation(axi.class))) {
            return true;
        }
        if ((this.d || !b(cls)) && !a(cls)) {
            Iterator<awf> it = (z ? this.f : this.g).iterator();
            while (it.hasNext()) {
                if (it.next().a(cls)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private boolean a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean b(Class<?> cls) {
        return cls.isMemberClass() && !c(cls);
    }

    private boolean c(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean a(axh axhVar, axi axiVar) {
        return a(axhVar) && a(axiVar);
    }

    private boolean a(axh axhVar) {
        return axhVar == null || axhVar.a() <= this.b;
    }

    private boolean a(axi axiVar) {
        return axiVar == null || axiVar.a() > this.b;
    }
}
