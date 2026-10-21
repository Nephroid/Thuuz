package defpackage;

import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.CustomProperty;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class mi {
    private final Map<CustomPropertyKey, CustomProperty> a = new HashMap();

    public AppVisibleCustomProperties a() {
        return new AppVisibleCustomProperties(this.a.values());
    }
}
