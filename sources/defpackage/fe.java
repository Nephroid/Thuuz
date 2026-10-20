package defpackage;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class fe {
    Map<String, String> a = new HashMap();

    public Map<String, String> a(String str) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : this.a.entrySet()) {
            hashMap.put(str + entry.getKey(), entry.getValue());
        }
        return hashMap;
    }
}
