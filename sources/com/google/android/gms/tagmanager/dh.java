package com.google.android.gms.tagmanager;

/* loaded from: classes.dex */
public class dh extends Number implements Comparable<dh> {
    private double arG;
    private long arH;
    private boolean arI = true;

    private dh(long j) {
        this.arH = j;
    }

    public static dh a(long j) {
        return new dh(j);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(dh dhVar) {
        return (b() && dhVar.b()) ? new Long(this.arH).compareTo(Long.valueOf(dhVar.arH)) : Double.compare(doubleValue(), dhVar.doubleValue());
    }

    public boolean a() {
        return !b();
    }

    public boolean b() {
        return this.arI;
    }

    @Override // java.lang.Number
    public byte byteValue() {
        return (byte) longValue();
    }

    public long c() {
        return b() ? this.arH : (long) this.arG;
    }

    public int d() {
        return (int) longValue();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return b() ? this.arH : this.arG;
    }

    public short e() {
        return (short) longValue();
    }

    public boolean equals(Object obj) {
        return (obj instanceof dh) && compareTo((dh) obj) == 0;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) doubleValue();
    }

    public int hashCode() {
        return new Long(longValue()).hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        return d();
    }

    @Override // java.lang.Number
    public long longValue() {
        return c();
    }

    @Override // java.lang.Number
    public short shortValue() {
        return e();
    }

    public String toString() {
        return b() ? Long.toString(this.arH) : Double.toString(this.arG);
    }
}
