package defpackage;

import com.flurry.android.Constants;
import java.util.Comparator;

/* loaded from: classes.dex */
final class ih implements Comparator<byte[]> {
    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(byte[] bArr, byte[] bArr2) {
        int min = Math.min(bArr.length, bArr2.length);
        for (int i = 0; i < min; i++) {
            int i2 = bArr[i] & Constants.UNKNOWN;
            int i3 = bArr2[i] & Constants.UNKNOWN;
            if (i2 != i3) {
                return i2 - i3;
            }
        }
        return bArr.length - bArr2.length;
    }
}
