package defpackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes.dex */
public abstract class td {
    private static MessageDigest b = null;
    protected Object a = new Object();

    /* JADX INFO: Access modifiers changed from: protected */
    public MessageDigest a() {
        MessageDigest messageDigest;
        synchronized (this.a) {
            if (b != null) {
                messageDigest = b;
            } else {
                for (int i = 0; i < 2; i++) {
                    try {
                        b = MessageDigest.getInstance("MD5");
                    } catch (NoSuchAlgorithmException e) {
                    }
                }
                messageDigest = b;
            }
        }
        return messageDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] a(String str);
}
