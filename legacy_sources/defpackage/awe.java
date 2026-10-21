package defpackage;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes.dex */
final class awe implements awt<Date>, awz<Date> {
    private final DateFormat a;
    private final DateFormat b;
    private final DateFormat c;

    awe() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public awe(String str) {
        this(new SimpleDateFormat(str, Locale.US), new SimpleDateFormat(str));
    }

    public awe(int i, int i2) {
        this(DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    awe(DateFormat dateFormat, DateFormat dateFormat2) {
        this.a = dateFormat;
        this.b = dateFormat2;
        this.c = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        this.c.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override // defpackage.awz
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public awu serialize(Date date, Type type, awy awyVar) {
        awx awxVar;
        synchronized (this.b) {
            awxVar = new awx(this.a.format(date));
        }
        return awxVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(awe.class.getSimpleName());
        sb.append('(').append(this.b.getClass().getSimpleName()).append(')');
        return sb.toString();
    }
}
