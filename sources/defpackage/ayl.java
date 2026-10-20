package defpackage;

/* loaded from: classes.dex */
class ayl implements CharSequence {
    char[] a;

    @Override // java.lang.CharSequence
    public int length() {
        return this.a.length;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.a[i];
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return new String(this.a, i, i2 - i);
    }
}
