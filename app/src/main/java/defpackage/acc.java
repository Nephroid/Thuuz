package defpackage;

import com.facebook.AppEventsConstants;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
@zy
/* loaded from: classes.dex */
public class acc {
    private final List<String> a;
    private final List<String> b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final boolean g;
    private final int h;
    private String i;
    private int j;

    public acc(int i, Map<String, String> map) {
        this.i = map.get(NativeProtocol.IMAGE_URL_KEY);
        this.d = map.get("base_uri");
        this.e = map.get("post_parameters");
        this.g = b(map.get("drt_include"));
        this.c = map.get("activation_overlay_url");
        this.b = c(map.get("check_packages"));
        this.h = d(map.get("request_id"));
        this.f = map.get("type");
        this.a = c(map.get("errors"));
        this.j = i;
    }

    private static boolean b(String str) {
        return str != null && (str.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) || str.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
    }

    private List<String> c(String str) {
        if (str == null) {
            return null;
        }
        return Arrays.asList(str.split(","));
    }

    private int d(String str) {
        if (str == null) {
            return 0;
        }
        return Integer.parseInt(str);
    }

    public int a() {
        return this.j;
    }

    public void a(String str) {
        this.i = str;
    }

    public List<String> b() {
        return this.a;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.i;
    }

    public String e() {
        return this.f;
    }

    public boolean f() {
        return this.g;
    }
}
