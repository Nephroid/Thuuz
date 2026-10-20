package defpackage;

import com.google.gson.JsonIOException;
import java.io.IOException;

/* loaded from: classes.dex */
public abstract class axd<T> {
    public abstract void a(bav bavVar, T t);

    public final awu a(T t) {
        try {
            ayx ayxVar = new ayx();
            a(ayxVar, t);
            return ayxVar.a();
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }
}
