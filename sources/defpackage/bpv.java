package defpackage;

/* loaded from: classes.dex */
public class bpv implements Comparable<bpv> {
    private String a;
    private Integer b;
    private int c;

    public bpv(String str, int i, Integer num) {
        this.a = str;
        this.c = i;
        this.b = num;
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.c;
    }

    public Integer c() {
        return this.b;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bpv bpvVar) {
        if (c() == null) {
            return 1;
        }
        if (bpvVar.c() == null) {
            return -1;
        }
        int compareTo = bpvVar.c().compareTo(c());
        if (compareTo == 0) {
            return Integer.valueOf(b()).compareTo(Integer.valueOf(bpvVar.b()));
        }
        return compareTo;
    }
}
