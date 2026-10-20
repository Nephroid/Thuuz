package defpackage;

import java.sql.Timestamp;
import java.util.Date;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class bab implements axe {
    @Override // defpackage.axe
    public <T> axd<T> a(awi awiVar, bau<T> bauVar) {
        if (bauVar.a() != Timestamp.class) {
            return null;
        }
        return new bac(this, awiVar.a((Class) Date.class));
    }
}
