package defpackage;

/* loaded from: classes.dex */
class gm implements eq {
    private final long a;
    private final int b;
    private double c;
    private long d;
    private final Object e;
    private final String f;

    public gm(int i, long j, String str) {
        this.e = new Object();
        this.b = i;
        this.c = this.b;
        this.a = j;
        this.f = str;
    }

    public gm(String str) {
        this(60, 2000L, str);
    }

    @Override // defpackage.eq
    public boolean a() {
        boolean z;
        synchronized (this.e) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.c < this.b) {
                double d = (currentTimeMillis - this.d) / this.a;
                if (d > 0.0d) {
                    this.c = Math.min(this.b, d + this.c);
                }
            }
            this.d = currentTimeMillis;
            if (this.c >= 1.0d) {
                this.c -= 1.0d;
                z = true;
            } else {
                gn.d("Excessive " + this.f + " detected; call ignored.");
                z = false;
            }
        }
        return z;
    }
}
