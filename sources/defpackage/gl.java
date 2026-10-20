package defpackage;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
class gl {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(gk gkVar, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(gkVar.a());
        if (gkVar.c() > 0) {
            long c = j - gkVar.c();
            if (c >= 0) {
                sb.append("&qt").append("=").append(c);
            }
        }
        sb.append("&z").append("=").append(gkVar.b());
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str) {
        try {
            return URLEncoder.encode(str, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("URL encoding failed for: " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, String> a(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().startsWith("&") && entry.getValue() != null) {
                String substring = entry.getKey().substring(1);
                if (!TextUtils.isEmpty(substring)) {
                    hashMap.put(substring, entry.getValue());
                }
            }
        }
        return hashMap;
    }
}
