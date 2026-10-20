package defpackage;

import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigInteger;

/* loaded from: classes.dex */
public final class awx extends awu {
    private static final Class<?>[] a = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object b;

    public awx(Boolean bool) {
        a(bool);
    }

    public awx(Number number) {
        a(number);
    }

    public awx(String str) {
        a(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public awx(Object obj) {
        a(obj);
    }

    void a(Object obj) {
        if (obj instanceof Character) {
            this.b = String.valueOf(((Character) obj).charValue());
        } else {
            axj.a((obj instanceof Number) || b(obj));
            this.b = obj;
        }
    }

    public boolean l() {
        return this.b instanceof Boolean;
    }

    @Override // defpackage.awu
    Boolean k() {
        return (Boolean) this.b;
    }

    @Override // defpackage.awu
    public boolean c() {
        return l() ? k().booleanValue() : Boolean.parseBoolean(b());
    }

    public boolean m() {
        return this.b instanceof Number;
    }

    @Override // defpackage.awu
    public Number a() {
        return this.b instanceof String ? new LazilyParsedNumber((String) this.b) : (Number) this.b;
    }

    public boolean n() {
        return this.b instanceof String;
    }

    @Override // defpackage.awu
    public String b() {
        if (m()) {
            return a().toString();
        }
        if (l()) {
            return k().toString();
        }
        return (String) this.b;
    }

    private static boolean b(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class<?> cls = obj.getClass();
        for (Class<?> cls2 : a) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.b == null) {
            return 31;
        }
        if (a(this)) {
            long longValue = a().longValue();
            return (int) (longValue ^ (longValue >>> 32));
        }
        if (this.b instanceof Number) {
            long doubleToLongBits = Double.doubleToLongBits(a().doubleValue());
            return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        }
        return this.b.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        awx awxVar = (awx) obj;
        if (this.b == null) {
            return awxVar.b == null;
        }
        if (a(this) && a(awxVar)) {
            return a().longValue() == awxVar.a().longValue();
        }
        if ((this.b instanceof Number) && (awxVar.b instanceof Number)) {
            double doubleValue = a().doubleValue();
            double doubleValue2 = awxVar.a().doubleValue();
            if (doubleValue == doubleValue2 || (Double.isNaN(doubleValue) && Double.isNaN(doubleValue2))) {
                z = true;
            }
            return z;
        }
        return this.b.equals(awxVar.b);
    }

    private static boolean a(awx awxVar) {
        if (!(awxVar.b instanceof Number)) {
            return false;
        }
        Number number = (Number) awxVar.b;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }
}
