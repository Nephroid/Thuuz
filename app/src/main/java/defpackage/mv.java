package defpackage;

import com.google.android.gms.drive.UserMetadata;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/* loaded from: classes.dex */
public class mv extends ms<UserMetadata> {
    public mv(String str, int i) {
        super(str, a(str), Collections.emptyList(), i);
    }

    private static String a(String str, String str2) {
        return str + "." + str2;
    }

    private static Collection<String> a(String str) {
        return Arrays.asList(a(str, "permissionId"), a(str, "displayName"), a(str, "picture"), a(str, "isAuthenticatedUser"), a(str, "emailAddress"));
    }
}
