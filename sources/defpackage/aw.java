package defpackage;

import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public class aw {
    public static v a(ae aeVar) {
        long j;
        boolean z = false;
        long j2 = 0;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = aeVar.c;
        String str = map.get(HTTP.DATE_HEADER);
        long a = str != null ? a(str) : 0L;
        String str2 = map.get("Cache-Control");
        if (str2 != null) {
            String[] split = str2.split(",");
            long j3 = 0;
            for (String str3 : split) {
                String trim = str3.trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j3 = Long.parseLong(trim.substring(8));
                    } catch (Exception e) {
                    }
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    j3 = 0;
                }
            }
            j = j3;
            z = true;
        } else {
            j = 0;
        }
        String str4 = map.get("Expires");
        long a2 = str4 != null ? a(str4) : 0L;
        String str5 = map.get("ETag");
        if (z) {
            j2 = (1000 * j) + currentTimeMillis;
        } else if (a > 0 && a2 >= a) {
            j2 = (a2 - a) + currentTimeMillis;
        }
        v vVar = new v();
        vVar.a = aeVar.b;
        vVar.b = str5;
        vVar.e = j2;
        vVar.d = vVar.e;
        vVar.c = a;
        vVar.f = map;
        return vVar;
    }

    public static long a(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException e) {
            return 0L;
        }
    }

    public static String a(Map<String, String> map) {
        String str = map.get(HTTP.CONTENT_TYPE);
        if (str != null) {
            String[] split = str.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return "ISO-8859-1";
    }
}
