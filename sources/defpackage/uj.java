package defpackage;

import android.text.TextUtils;

@zy
/* loaded from: classes.dex */
public final class uj {
    private String a;
    private String b;
    private String c;

    public uj() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/sdk-core-v40.html";
        this.b = null;
        this.c = null;
    }

    public uj(String str, String str2, String str3) {
        this.a = null;
        this.b = null;
        this.c = null;
        if (TextUtils.isEmpty(str)) {
            this.a = "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/sdk-core-v40.html";
        } else {
            this.a = str;
        }
        this.b = str2;
        this.c = str3;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
