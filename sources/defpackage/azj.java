package defpackage;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public final class azj extends axd<Time> {
    public static final axe a = new azk();
    private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");

    @Override // defpackage.axd
    public synchronized void a(bav bavVar, Time time) {
        bavVar.b(time == null ? null : this.b.format((Date) time));
    }
}
