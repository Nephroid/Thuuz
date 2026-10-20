package defpackage;

import com.google.android.gms.internal.hb;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class gb {
    private final Map<String, String> a;
    private final long b;
    private final String c;
    private final List<hb> d;

    public gb(Map<String, String> map, long j, String str, List<hb> list) {
        this.a = map;
        this.b = j;
        this.c = str;
        this.d = list;
    }

    public Map<String, String> a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public List<hb> d() {
        return this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PATH: ");
        sb.append(this.c);
        if (this.a != null) {
            sb.append("  PARAMS: ");
            for (Map.Entry<String, String> entry : this.a.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append(",  ");
            }
        }
        return sb.toString();
    }
}
