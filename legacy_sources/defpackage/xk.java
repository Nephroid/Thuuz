package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.internal.dj;

@zy
/* loaded from: classes.dex */
public final class xk {
    public static boolean a(Context context, dj djVar, xt xtVar) {
        if (djVar == null) {
            adn.e("No intent data for launcher overlay.");
            return false;
        }
        Intent intent = new Intent();
        if (TextUtils.isEmpty(djVar.c)) {
            adn.e("Open GMSG did not contain a URL.");
            return false;
        }
        if (TextUtils.isEmpty(djVar.d)) {
            intent.setData(Uri.parse(djVar.c));
        } else {
            intent.setDataAndType(Uri.parse(djVar.c), djVar.d);
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty(djVar.e)) {
            intent.setPackage(djVar.e);
        }
        if (!TextUtils.isEmpty(djVar.f)) {
            String[] split = djVar.f.split("/", 2);
            if (split.length < 2) {
                adn.e("Could not parse component name from open GMSG: " + djVar.f);
                return false;
            }
            intent.setClassName(split[0], split[1]);
        }
        try {
            adn.d("Launching an intent: " + intent);
            context.startActivity(intent);
            xtVar.t();
            return true;
        } catch (ActivityNotFoundException e) {
            adn.e(e.getMessage());
            return false;
        }
    }
}
