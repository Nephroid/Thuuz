package defpackage;

import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpw extends bpq {
    public bpw(String str, String str2, String str3, String str4) {
        super(new JSONObject());
        b("id", str);
        b("first_name", str2);
        b("last_name", str3);
        b("email", str4);
    }

    public String a() {
        return e("id");
    }

    public String b() {
        return e("first_name");
    }

    public String c() {
        return e("last_name");
    }

    public String d() {
        return e("email");
    }

    public String e() {
        return "http://graph.facebook.com/" + a() + "/picture";
    }
}
