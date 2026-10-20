package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;

@zy
/* loaded from: classes.dex */
public class yb {
    private Object a;
    private final Context b;

    public yb(Context context) {
        this.b = context;
    }

    public int a(String str, String str2) {
        try {
            Class<?> loadClass = this.b.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return ((Integer) loadClass.getDeclaredMethod("consumePurchase", Integer.TYPE, String.class, String.class).invoke(loadClass.cast(this.a), 3, str, str2)).intValue();
        } catch (Exception e) {
            adn.d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            return 5;
        }
    }

    public Bundle a(String str, String str2, String str3) {
        try {
            Class<?> loadClass = this.b.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (Bundle) loadClass.getDeclaredMethod("getBuyIntent", Integer.TYPE, String.class, String.class, String.class, String.class).invoke(loadClass.cast(this.a), 3, str, str2, "inapp", str3);
        } catch (Exception e) {
            adn.d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            return null;
        }
    }

    public void a() {
        this.a = null;
    }

    public void a(IBinder iBinder) {
        try {
            this.a = this.b.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, iBinder);
        } catch (Exception e) {
            adn.e("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.");
        }
    }

    public Bundle b(String str, String str2) {
        try {
            Class<?> loadClass = this.b.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (Bundle) loadClass.getDeclaredMethod("getPurchases", Integer.TYPE, String.class, String.class, String.class).invoke(loadClass.cast(this.a), 3, str, "inapp", str2);
        } catch (Exception e) {
            adn.d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            return null;
        }
    }
}
