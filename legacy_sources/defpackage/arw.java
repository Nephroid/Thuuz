package defpackage;

import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class arw implements arr {
    final /* synthetic */ arv a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public arw(arv arvVar) {
        this.a = arvVar;
    }

    @Override // defpackage.arr
    public void a(Map<String, Object> map) {
        Object obj = map.get("event");
        if (obj != null) {
            this.a.a(obj.toString());
        }
    }
}
