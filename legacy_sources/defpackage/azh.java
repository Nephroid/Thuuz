package defpackage;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* loaded from: classes.dex */
public final class azh extends axd<Date> {
    public static final axe a = new azi();
    private final DateFormat b = new SimpleDateFormat("MMM d, yyyy");

    @Override // defpackage.axd
    public synchronized void a(bav bavVar, Date date) {
        bavVar.b(date == null ? null : this.b.format((java.util.Date) date));
    }
}
