package defpackage;

import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class arq {
    public final String a;
    public final Object b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public arq(String str, Object obj) {
        this.a = str;
        this.b = obj;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof arq)) {
            return false;
        }
        arq arqVar = (arq) obj;
        return this.a.equals(arqVar.a) && this.b.equals(arqVar.b);
    }

    public int hashCode() {
        return Arrays.hashCode(new Integer[]{Integer.valueOf(this.a.hashCode()), Integer.valueOf(this.b.hashCode())});
    }

    public String toString() {
        return "Key: " + this.a + " value: " + this.b.toString();
    }
}
