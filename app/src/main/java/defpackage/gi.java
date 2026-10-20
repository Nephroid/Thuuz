package defpackage;

/* loaded from: classes.dex */
class gi implements fn<gj> {
    private final gj a = new gj();

    @Override // defpackage.fn
    public void a(String str, int i) {
        if ("ga_dispatchPeriod".equals(str)) {
            this.a.d = i;
        } else {
            gn.d("int configuration name not recognized:  " + str);
        }
    }

    @Override // defpackage.fn
    public void a(String str, String str2) {
    }

    @Override // defpackage.fn
    public void a(String str, boolean z) {
        if (!"ga_dryRun".equals(str)) {
            gn.d("bool configuration name not recognized:  " + str);
        } else {
            this.a.e = z ? 1 : 0;
        }
    }

    @Override // defpackage.fn
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public gj a() {
        return this.a;
    }

    @Override // defpackage.fn
    public void b(String str, String str2) {
        if ("ga_appName".equals(str)) {
            this.a.a = str2;
            return;
        }
        if ("ga_appVersion".equals(str)) {
            this.a.b = str2;
        } else if ("ga_logLevel".equals(str)) {
            this.a.c = str2;
        } else {
            gn.d("string configuration name not recognized:  " + str);
        }
    }
}
