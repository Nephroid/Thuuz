package defpackage;

import android.support.v4.view.MotionEventCompat;
import java.security.MessageDigest;

/* loaded from: classes.dex */
public class ti extends td {
    private MessageDigest b;

    @Override // defpackage.td
    public byte[] a(String str) {
        byte[] bArr;
        byte[] a = a(str.split(" "));
        this.b = a();
        synchronized (this.a) {
            if (this.b == null) {
                bArr = new byte[0];
            } else {
                this.b.reset();
                this.b.update(a);
                byte[] digest = this.b.digest();
                bArr = new byte[digest.length <= 4 ? digest.length : 4];
                System.arraycopy(digest, 0, bArr, 0, bArr.length);
            }
        }
        return bArr;
    }

    byte[] a(String[] strArr) {
        byte[] bArr = new byte[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            bArr[i] = (byte) (th.a(strArr[i]) & MotionEventCompat.ACTION_MASK);
        }
        return bArr;
    }
}
