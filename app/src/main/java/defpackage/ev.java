package defpackage;

import android.app.Activity;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ev implements fl {
    Map<String, String> a;

    public String a(Activity activity) {
        return a(activity.getClass().getCanonicalName());
    }

    public String a(String str) {
        String str2 = this.a.get(str);
        return str2 != null ? str2 : str;
    }
}
