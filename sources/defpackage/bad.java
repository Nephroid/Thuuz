package defpackage;

import java.util.Calendar;

/* loaded from: classes.dex */
final class bad extends axd<Calendar> {
    @Override // defpackage.axd
    public void a(bav bavVar, Calendar calendar) {
        if (calendar == null) {
            bavVar.f();
            return;
        }
        bavVar.d();
        bavVar.a("year");
        bavVar.a(calendar.get(1));
        bavVar.a("month");
        bavVar.a(calendar.get(2));
        bavVar.a("dayOfMonth");
        bavVar.a(calendar.get(5));
        bavVar.a("hourOfDay");
        bavVar.a(calendar.get(11));
        bavVar.a("minute");
        bavVar.a(calendar.get(12));
        bavVar.a("second");
        bavVar.a(calendar.get(13));
        bavVar.e();
    }
}
