package defpackage;

import android.text.TextUtils;
import android.util.Base64;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@zy
/* loaded from: classes.dex */
public class yn {
    public static PublicKey a(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e2) {
            adn.b("Invalid key specification.");
            throw new IllegalArgumentException(e2);
        }
    }

    public static boolean a(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3)) {
            return a(a(str), str2, str3);
        }
        adn.b("Purchase verification failed: missing data.");
        return false;
    }

    public static boolean a(PublicKey publicKey, String str, String str2) {
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(publicKey);
            signature.update(str.getBytes());
            if (signature.verify(Base64.decode(str2, 0))) {
                return true;
            }
            adn.b("Signature verification failed.");
            return false;
        } catch (InvalidKeyException e) {
            adn.b("Invalid key specification.");
            return false;
        } catch (NoSuchAlgorithmException e2) {
            adn.b("NoSuchAlgorithmException.");
            return false;
        } catch (SignatureException e3) {
            adn.b("Signature exception.");
            return false;
        }
    }
}
