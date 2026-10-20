package defpackage;

import android.content.Context;
import android.net.Uri;
import com.facebook.internal.NativeProtocol;
import java.util.Map;

/* loaded from: classes.dex */
class ato implements arr {
    private final Context a;

    public ato(Context context) {
        this.a = context;
    }

    @Override // defpackage.arr
    public void a(Map<String, Object> map) {
        String queryParameter;
        Object obj;
        Object obj2 = map.get("gtm.url");
        Object obj3 = (obj2 == null && (obj = map.get("gtm")) != null && (obj instanceof Map)) ? ((Map) obj).get(NativeProtocol.IMAGE_URL_KEY) : obj2;
        if (obj3 == null || !(obj3 instanceof String) || (queryParameter = Uri.parse((String) obj3).getQueryParameter("referrer")) == null) {
            return;
        }
        asi.b(this.a, queryParameter);
    }
}
