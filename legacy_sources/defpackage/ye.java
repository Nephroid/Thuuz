package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@zy
/* loaded from: classes.dex */
public final class ye extends yp {
    private String a;
    private Context b;
    private String c;
    private ArrayList<String> d;

    public ye(String str, ArrayList<String> arrayList, Context context, String str2) {
        this.c = str;
        this.d = arrayList;
        this.a = str2;
        this.b = context;
    }

    private void b() {
        try {
            this.b.getClassLoader().loadClass("com.google.ads.conversiontracking.IAPConversionReporter").getDeclaredMethod("reportWithProductId", Context.class, String.class, String.class, Boolean.TYPE).invoke(null, this.b, this.c, "", true);
        } catch (ClassNotFoundException e) {
            adn.e("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (NoSuchMethodException e2) {
            adn.e("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (Exception e3) {
            adn.d("Fail to report a conversion.", e3);
        }
    }

    protected int a(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        return i == 4 ? 3 : 0;
    }

    @Override // defpackage.yo
    public String a() {
        return this.c;
    }

    protected String a(String str, HashMap<String, String> hashMap) {
        String str2;
        String packageName = this.b.getPackageName();
        try {
            str2 = this.b.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            adn.d("Error to retrieve app version", e);
            str2 = "";
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - acl.e().a();
        for (String str3 : hashMap.keySet()) {
            str = str.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", str3), String.format("$1%s$2", hashMap.get(str3)));
        }
        return str.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "sessionid"), String.format("$1%s$2", acl.a)).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "appid"), String.format("$1%s$2", packageName)).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "osversion"), String.format("$1%s$2", String.valueOf(Build.VERSION.SDK_INT))).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "sdkversion"), String.format("$1%s$2", this.a)).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "appversion"), String.format("$1%s$2", str2)).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "timestamp"), String.format("$1%s$2", String.valueOf(elapsedRealtime))).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "[^@]+"), String.format("$1%s$2", "")).replaceAll("@@", "@");
    }

    @Override // defpackage.yo
    public void b(int i) {
        if (i == 1) {
            b();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("status", String.valueOf(i));
        hashMap.put("sku", this.c);
        Iterator<String> it = this.d.iterator();
        while (it.hasNext()) {
            new adl(this.b, this.a, a(it.next(), hashMap)).e();
        }
    }

    @Override // defpackage.yo
    public void c(int i) {
        if (i == 0) {
            b();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("google_play_status", String.valueOf(i));
        hashMap.put("sku", this.c);
        hashMap.put("status", String.valueOf(a(i)));
        Iterator<String> it = this.d.iterator();
        while (it.hasNext()) {
            new adl(this.b, this.a, a(it.next(), hashMap)).e();
        }
    }
}
