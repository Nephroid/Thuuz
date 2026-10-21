package defpackage;

import java.util.logging.Logger;

/* loaded from: classes.dex */
public class cci {
    public static Logger a(Class cls) {
        return Logger.getLogger(cls.getSimpleName() + ":thuuz");
    }
}
