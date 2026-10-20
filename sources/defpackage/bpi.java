package defpackage;

/* loaded from: classes.dex */
public class bpi {
    public int a;
    public String b;
    public String c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bpi(int i, String str, String str2) {
        this.a = i;
        this.b = str;
        this.c = str2;
    }

    public String a() {
        boolean z = this.b.length() > 0;
        boolean z2 = this.c.length() > 0;
        if (z) {
            String str = this.b;
            if (z2) {
                return str + " (" + this.c + ")";
            }
            return str;
        }
        if (!z2) {
            return "???";
        }
        return this.c;
    }
}
