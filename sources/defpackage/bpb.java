package defpackage;

import org.joda.time.DateTime;

/* loaded from: classes.dex */
public class bpb {
    private String a;
    private int b;
    private int c;
    private DateTime d;
    private DateTime e;

    public bpb(int i, DateTime dateTime, String str, int i2, DateTime dateTime2) {
        this.a = str;
        this.b = i2;
        this.c = i;
        this.d = dateTime;
        this.e = dateTime2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bpb bpbVar = (bpb) obj;
        if (this.b == bpbVar.b && this.c == bpbVar.c) {
            if (this.e == null ? bpbVar.e != null : !this.e.equals(bpbVar.e)) {
                return false;
            }
            if (this.a == null ? bpbVar.a != null : !this.a.equals(bpbVar.a)) {
                return false;
            }
            if (this.d != null) {
                if (this.d.equals(bpbVar.d)) {
                    return true;
                }
            } else if (bpbVar.d == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return (((this.d != null ? this.d.hashCode() : 0) + ((((((this.a != null ? this.a.hashCode() : 0) * 31) + this.b) * 31) + this.c) * 31)) * 31) + (this.e != null ? this.e.hashCode() : 0);
    }

    public DateTime a() {
        return this.e;
    }

    public int b() {
        return this.b;
    }

    public DateTime c() {
        return this.d;
    }

    public int d() {
        return this.c;
    }
}
