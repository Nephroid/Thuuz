package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class atj extends ati {
    private static final Object a = new Object();
    private static atj k;
    private asg b;
    private volatile asf c;
    private int d = 1800000;
    private boolean e = true;
    private boolean f = false;
    private boolean g = true;
    private boolean h = true;
    private ash i = new atk(this);
    private boolean j = false;

    private atj() {
    }

    public static atj b() {
        if (k == null) {
            k = new atj();
        }
        return k;
    }

    @Override // defpackage.ati
    public synchronized void a() {
        if (this.f) {
            this.c.a(new atl(this));
        } else {
            asj.d("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.e = true;
        }
    }
}
