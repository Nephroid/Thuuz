package defpackage;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
class en {
    private final Map<String, Integer> a = new HashMap();
    private final Map<String, String> b = new HashMap();
    private final boolean c;
    private final String d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public en(String str, boolean z) {
        this.c = z;
        this.d = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a() {
        if (!this.c) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.d);
        for (String str : this.a.keySet()) {
            sb.append("&").append(str).append("=").append(this.a.get(str));
        }
        for (String str2 : this.b.keySet()) {
            sb.append("&").append(str2).append("=").append(this.b.get(str2));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, int i) {
        if (this.c) {
            Integer num = this.a.get(str);
            if (num == null) {
                num = 0;
            }
            this.a.put(str, Integer.valueOf(num.intValue() + i));
        }
    }
}
