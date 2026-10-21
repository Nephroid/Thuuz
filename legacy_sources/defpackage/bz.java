package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class bz {
    private static bz b = new bz();
    List<ca> a = new ArrayList();

    public static bz a() {
        return b;
    }

    private bz() {
    }

    public void a(String str) {
        this.a.add(new ca(str));
    }
}
