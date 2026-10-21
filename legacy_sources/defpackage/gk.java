package defpackage;

import android.text.TextUtils;

/* loaded from: classes.dex */
class gk {
    private String a;
    private final long b;
    private final long c;
    private String d = "https:";

    /* JADX INFO: Access modifiers changed from: package-private */
    public gk(String str, long j, long j2) {
        this.a = str;
        this.b = j;
        this.c = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        this.a = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long b() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(String str) {
        if (str == null || TextUtils.isEmpty(str.trim()) || !str.toLowerCase().startsWith("http:")) {
            return;
        }
        this.d = "http:";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long c() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String d() {
        return this.d;
    }
}
