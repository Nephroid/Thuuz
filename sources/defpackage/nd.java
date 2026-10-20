package defpackage;

import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Set;

/* loaded from: classes.dex */
public class nd {
    public static ly<?> a(MetadataBundle metadataBundle) {
        Set<ly<?>> a = metadataBundle.a();
        if (a.size() != 1) {
            throw new IllegalArgumentException("bundle should have exactly 1 populated field");
        }
        return a.iterator().next();
    }
}
