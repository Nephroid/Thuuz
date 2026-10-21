package defpackage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.protocol.HTTP;
import org.joda.time.DateTimeConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class atr {
    private static ast<ww> a(ast<ww> astVar) {
        try {
            return new ast<>(atp.c(a(atp.a(astVar.a()))), astVar.b());
        } catch (UnsupportedEncodingException e) {
            asj.a("Escape URI: unsupported encoding", e);
            return astVar;
        }
    }

    private static ast<ww> a(ast<ww> astVar, int i) {
        if (!a(astVar.a())) {
            asj.a("Escaping can only be applied to strings.");
            return astVar;
        }
        switch (i) {
            case DateTimeConstants.DECEMBER /* 12 */:
                return a(astVar);
            default:
                asj.a("Unsupported Value Escaping: " + i);
                return astVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ast<ww> a(ast<ww> astVar, int... iArr) {
        for (int i : iArr) {
            astVar = a(astVar, i);
        }
        return astVar;
    }

    static String a(String str) {
        return URLEncoder.encode(str, HTTP.UTF_8).replaceAll("\\+", "%20");
    }

    private static boolean a(ww wwVar) {
        return atp.c(wwVar) instanceof String;
    }
}
