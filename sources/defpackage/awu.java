package defpackage;

import java.io.IOException;
import java.io.StringWriter;

/* loaded from: classes.dex */
public abstract class awu {
    public boolean d() {
        return this instanceof awr;
    }

    public boolean e() {
        return this instanceof aww;
    }

    public boolean f() {
        return this instanceof awx;
    }

    public boolean g() {
        return this instanceof awv;
    }

    public aww h() {
        if (e()) {
            return (aww) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public awr i() {
        if (d()) {
            return (awr) this;
        }
        throw new IllegalStateException("This is not a JSON Array.");
    }

    public awx j() {
        if (f()) {
            return (awx) this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }

    public boolean c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    Boolean k() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Number a() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            bav bavVar = new bav(stringWriter);
            bavVar.b(true);
            ayi.a(this, bavVar);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
