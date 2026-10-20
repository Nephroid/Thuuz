package com.google.android.gms.ads.purchase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.en;
import defpackage.adn;
import defpackage.yu;

/* loaded from: classes.dex */
public final class InAppPurchaseActivity extends Activity {
    private yu a;

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        try {
            if (this.a != null) {
                this.a.a(i, i2, intent);
            }
        } catch (RemoteException e) {
            adn.d("Could not forward onActivityResult to in-app purchase manager:", e);
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = en.a((Activity) this);
        if (this.a == null) {
            adn.e("Could not create in-app purchase manager.");
            finish();
            return;
        }
        try {
            this.a.a();
        } catch (RemoteException e) {
            adn.d("Could not forward onCreate to in-app purchase manager:", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        try {
            if (this.a != null) {
                this.a.b();
            }
        } catch (RemoteException e) {
            adn.d("Could not forward onDestroy to in-app purchase manager:", e);
        }
        super.onDestroy();
    }
}
