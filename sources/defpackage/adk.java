package defpackage;

import android.content.Context;
import android.webkit.WebSettings;

@zy
/* loaded from: classes.dex */
public final class adk {
    public static String a(Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }

    public static void a(Context context, WebSettings webSettings) {
        adf.a(context, webSettings);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
    }
}
