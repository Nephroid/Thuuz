package com.google.android.gms.tagmanager;

import android.net.Uri;
import defpackage.asj;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public class ce {
    private static ce a;
    private volatile a b;
    private volatile String c;
    private volatile String d;
    private volatile String e;

    /* loaded from: classes.dex */
    public enum a {
        NONE,
        CONTAINER,
        CONTAINER_DEBUG
    }

    ce() {
        e();
    }

    public static ce a() {
        ce ceVar;
        synchronized (ce.class) {
            if (a == null) {
                a = new ce();
            }
            ceVar = a;
        }
        return ceVar;
    }

    private String a(String str) {
        return str.split("&")[0].split("=")[1];
    }

    private String b(Uri uri) {
        return uri.getQuery().replace("&gtm_debug=x", "");
    }

    public synchronized boolean a(Uri uri) {
        boolean z = true;
        synchronized (this) {
            try {
                String decode = URLDecoder.decode(uri.toString(), HTTP.UTF_8);
                if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                    asj.d("Container preview url: " + decode);
                    if (decode.matches(".*?&gtm_debug=x$")) {
                        this.b = a.CONTAINER_DEBUG;
                    } else {
                        this.b = a.CONTAINER;
                    }
                    this.e = b(uri);
                    if (this.b == a.CONTAINER || this.b == a.CONTAINER_DEBUG) {
                        this.d = "/r?" + this.e;
                    }
                    this.c = a(this.e);
                } else if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                    asj.b("Invalid preview uri: " + decode);
                    z = false;
                } else if (a(uri.getQuery()).equals(this.c)) {
                    asj.d("Exit preview mode for container: " + this.c);
                    this.b = a.NONE;
                    this.d = null;
                } else {
                    z = false;
                }
            } catch (UnsupportedEncodingException e) {
                z = false;
            }
        }
        return z;
    }

    public a b() {
        return this.b;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.c;
    }

    void e() {
        this.b = a.NONE;
        this.d = null;
        this.c = null;
        this.e = null;
    }
}
