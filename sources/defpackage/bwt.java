package defpackage;

/* loaded from: classes.dex */
public class bwt {
    public String a;
    public String b;
    public String c;

    public bwt(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public bwt(bpu bpuVar) {
        if (bpuVar != null) {
            this.a = bpuVar.d();
            this.b = bpuVar.e();
            this.c = bpuVar.k();
        }
    }

    public String toString() {
        return this.b;
    }
}
