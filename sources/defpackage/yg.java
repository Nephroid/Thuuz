package defpackage;

import android.util.Base64;

/* loaded from: classes.dex */
class yg implements aht {
    @Override // defpackage.aht
    public String a(byte[] bArr, boolean z) {
        return Base64.encodeToString(bArr, z ? 11 : 2);
    }

    @Override // defpackage.aht
    public byte[] a(String str, boolean z) {
        return Base64.decode(str, z ? 11 : 2);
    }
}
