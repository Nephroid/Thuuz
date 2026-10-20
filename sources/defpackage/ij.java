package defpackage;

import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ij {
    private final String[] a;
    private final ArrayList<HashMap<String, Object>> b;
    private final String c;
    private final HashMap<Object, Integer> d;
    private boolean e;
    private String f;

    private ij(String[] strArr, String str) {
        this.a = (String[]) kb.a(strArr);
        this.b = new ArrayList<>();
        this.c = str;
        this.d = new HashMap<>();
        this.e = false;
        this.f = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ ij(String[] strArr, String str, ii iiVar) {
        this(strArr, str);
    }
}
