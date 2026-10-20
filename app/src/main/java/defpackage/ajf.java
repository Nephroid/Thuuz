package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ajf {
    private static final ajg a = new ajg();
    private boolean b;
    private int[] c;
    private ajg[] d;
    private int e;

    public ajf() {
        this(10);
    }

    public ajf(int i) {
        this.b = false;
        int b = b(i);
        this.c = new int[b];
        this.d = new ajg[b];
        this.e = 0;
    }

    private boolean a(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private boolean a(ajg[] ajgVarArr, ajg[] ajgVarArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!ajgVarArr[i2].equals(ajgVarArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    private int b(int i) {
        return c(i * 4) / 4;
    }

    private int c(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            if (i <= (1 << i2) - 12) {
                return (1 << i2) - 12;
            }
        }
        return i;
    }

    private void c() {
        int i = this.e;
        int[] iArr = this.c;
        ajg[] ajgVarArr = this.d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            ajg ajgVar = ajgVarArr[i3];
            if (ajgVar != a) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    ajgVarArr[i2] = ajgVar;
                    ajgVarArr[i3] = null;
                }
                i2++;
            }
        }
        this.b = false;
        this.e = i2;
    }

    public int a() {
        if (this.b) {
            c();
        }
        return this.e;
    }

    public ajg a(int i) {
        if (this.b) {
            c();
        }
        return this.d[i];
    }

    public boolean b() {
        return a() == 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ajf)) {
            return false;
        }
        ajf ajfVar = (ajf) obj;
        if (a() != ajfVar.a()) {
            return false;
        }
        return a(this.c, ajfVar.c, this.e) && a(this.d, ajfVar.d, this.e);
    }

    public int hashCode() {
        if (this.b) {
            c();
        }
        int i = 17;
        for (int i2 = 0; i2 < this.e; i2++) {
            i = (((i * 31) + this.c[i2]) * 31) + this.d[i2].hashCode();
        }
        return i;
    }
}
