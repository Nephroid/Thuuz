package defpackage;

import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import com.google.android.gms.internal.l;

/* loaded from: classes.dex */
public class agc {
    private String a = "googleads.g.doubleclick.net";
    private String b = "/pagead/ads";
    private String c = "ad.doubleclick.net";
    private String[] d = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private aci e;

    public agc(aci aciVar) {
        this.e = aciVar;
    }

    private Uri a(Uri uri, Context context, String str, boolean z) {
        try {
            boolean a = a(uri);
            if (a) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new l("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new l("Query parameter already exists: ms");
            }
            String a2 = z ? this.e.a(context, str) : this.e.a(context);
            return a ? b(uri, "dc_ms", a2) : a(uri, "ms", a2);
        } catch (UnsupportedOperationException e) {
            throw new l("Provided Uri is not in a valid state");
        }
    }

    private Uri a(Uri uri, String str, String str2) {
        String uri2 = uri.toString();
        int indexOf = uri2.indexOf("&adurl");
        if (indexOf == -1) {
            indexOf = uri2.indexOf("?adurl");
        }
        return indexOf != -1 ? Uri.parse(uri2.substring(0, indexOf + 1) + str + "=" + str2 + "&" + uri2.substring(indexOf + 1)) : uri.buildUpon().appendQueryParameter(str, str2).build();
    }

    private Uri b(Uri uri, String str, String str2) {
        String uri2 = uri.toString();
        int indexOf = uri2.indexOf(";adurl");
        if (indexOf != -1) {
            return Uri.parse(uri2.substring(0, indexOf + 1) + str + "=" + str2 + ";" + uri2.substring(indexOf + 1));
        }
        String encodedPath = uri.getEncodedPath();
        int indexOf2 = uri2.indexOf(encodedPath);
        return Uri.parse(uri2.substring(0, encodedPath.length() + indexOf2) + ";" + str + "=" + str2 + ";" + uri2.substring(encodedPath.length() + indexOf2));
    }

    public aci a() {
        return this.e;
    }

    public Uri a(Uri uri, Context context) {
        try {
            return a(uri, context, uri.getQueryParameter("ai"), true);
        } catch (UnsupportedOperationException e) {
            throw new l("Provided Uri is not in a valid state");
        }
    }

    public void a(MotionEvent motionEvent) {
        this.e.a(motionEvent);
    }

    public boolean a(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.c);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean b(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            String host = uri.getHost();
            for (String str : this.d) {
                if (host.endsWith(str)) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
