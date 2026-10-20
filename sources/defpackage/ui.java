package defpackage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

@zy
/* loaded from: classes.dex */
public class ui {
    private final Context a;

    public ui(Context context) {
        kb.a(context, "Context can not be null");
        this.a = context;
    }

    public static boolean e() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public boolean a() {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return a(intent);
    }

    public boolean a(Intent intent) {
        kb.a(intent, "Intent can not be null");
        return !this.a.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
    }

    public boolean b() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("sms:"));
        return a(intent);
    }

    public boolean c() {
        return e() && this.a.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public boolean d() {
        return false;
    }

    public boolean f() {
        return Build.VERSION.SDK_INT >= 14 && a(new Intent("android.intent.action.INSERT").setType("vnd.android.cursor.dir/event"));
    }
}
