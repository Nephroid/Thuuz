package defpackage;

import java.util.Arrays;

/* loaded from: classes.dex */
class aub {
    final String a;
    final byte[] b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aub(String str, byte[] bArr) {
        this.a = str;
        this.b = bArr;
    }

    public String toString() {
        return "KeyAndSerialized: key = " + this.a + " serialized hash = " + Arrays.hashCode(this.b);
    }
}
