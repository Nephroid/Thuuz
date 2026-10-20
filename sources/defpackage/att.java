package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class att {
    private arm a;
    private atu b;
    private boolean c;

    public synchronized void a() {
        if (this.c) {
            asj.a("Refreshing a released ContainerHolder.");
        } else {
            this.b.a();
        }
    }

    public synchronized void a(String str) {
        if (!this.c) {
            this.a.a(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String b() {
        if (!this.c) {
            return this.a.a();
        }
        asj.a("getContainerId called on a released ContainerHolder.");
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(String str) {
        if (this.c) {
            asj.a("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.b.a(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String c() {
        if (!this.c) {
            return this.b.b();
        }
        asj.a("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }
}
