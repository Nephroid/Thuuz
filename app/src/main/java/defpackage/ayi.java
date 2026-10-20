package defpackage;

import java.io.Writer;

/* loaded from: classes.dex */
public final class ayi {
    public static void a(awu awuVar, bav bavVar) {
        azm.P.a(bavVar, awuVar);
    }

    public static Writer a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new ayk(appendable);
    }
}
