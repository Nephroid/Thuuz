package defpackage;

import com.google.android.gms.drive.query.internal.Operator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class my implements ne<String> {
    @Override // defpackage.ne
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String b() {
        return "all()";
    }

    @Override // defpackage.ne
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String b(Operator operator, List<String> list) {
        StringBuilder sb = new StringBuilder(operator.a() + "(");
        String str = "";
        Iterator<String> it = list.iterator();
        while (true) {
            String str2 = str;
            if (!it.hasNext()) {
                return sb.append(")").toString();
            }
            String next = it.next();
            sb.append(str2);
            sb.append(next);
            str = ",";
        }
    }

    @Override // defpackage.ne
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public <T> String b(Operator operator, ly<T> lyVar, T t) {
        return String.format("cmp(%s,%s,%s)", operator.a(), lyVar.a(), t);
    }

    @Override // defpackage.ne
    public String a(String str) {
        return String.format("not(%s)", str);
    }

    public String a(ly<?> lyVar) {
        return String.format("fieldOnly(%s)", lyVar.a());
    }

    public <T> String a(ly<T> lyVar, T t) {
        return String.format("has(%s,%s)", lyVar.a(), t);
    }

    public <T> String a(me<T> meVar, T t) {
        return String.format("contains(%s,%s)", meVar.a(), t);
    }

    @Override // defpackage.ne
    public /* synthetic */ String b(ly lyVar) {
        return a((ly<?>) lyVar);
    }

    @Override // defpackage.ne
    public /* synthetic */ String b(ly lyVar, Object obj) {
        return a((ly<ly>) lyVar, (ly) obj);
    }

    @Override // defpackage.ne
    public /* synthetic */ String b(me meVar, Object obj) {
        return a((me<me>) meVar, (me) obj);
    }
}
