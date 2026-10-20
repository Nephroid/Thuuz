package defpackage;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class aep {
    private static final String[] a = {"text1", "text2", "icon", "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity"};
    private static final Map<String, Integer> b = new HashMap(a.length);

    static {
        for (int i = 0; i < a.length; i++) {
            b.put(a[i], Integer.valueOf(i));
        }
    }

    public static String a(int i) {
        if (i < 0 || i >= a.length) {
            return null;
        }
        return a[i];
    }
}
