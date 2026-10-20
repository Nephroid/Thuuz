package defpackage;

import android.content.Intent;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public final class yl {
    public static int a(Intent intent) {
        Object obj = intent.getExtras().get("RESPONSE_CODE");
        if (obj == null) {
            adn.e("Intent with no response code, assuming OK (known issue)");
            return 0;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof Long) {
            return (int) ((Long) obj).longValue();
        }
        adn.e("Unexpected type for intent response code. " + obj.getClass().getName());
        return 5;
    }

    public static int a(Bundle bundle) {
        Object obj = bundle.get("RESPONSE_CODE");
        if (obj == null) {
            adn.e("Bundle with null response code, assuming OK (known issue)");
            return 0;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof Long) {
            return (int) ((Long) obj).longValue();
        }
        adn.e("Unexpected type for intent response code. " + obj.getClass().getName());
        return 5;
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str).getString("developerPayload");
        } catch (JSONException e) {
            adn.e("Fail to parse purchase data");
            return null;
        }
    }

    public static String b(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getStringExtra("INAPP_PURCHASE_DATA");
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str).getString("purchaseToken");
        } catch (JSONException e) {
            adn.e("Fail to parse purchase data");
            return null;
        }
    }

    public static String c(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getStringExtra("INAPP_DATA_SIGNATURE");
    }
}
