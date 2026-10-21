package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.g;
import defpackage.adn;
import defpackage.og;
import defpackage.yf;
import defpackage.yu;
import defpackage.yv;
import defpackage.yx;
import defpackage.yy;
import defpackage.zy;

@zy
/* loaded from: classes.dex */
public final class en extends g<yx> {
    private static final en a = new en();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    private en() {
        super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
    }

    public static yu a(Activity activity) {
        yu c;
        try {
            if (b(activity)) {
                adn.a("Using AdOverlay from the client jar.");
                c = new yf(activity);
            } else {
                c = a.c(activity);
            }
            return c;
        } catch (a e) {
            adn.e(e.getMessage());
            return null;
        }
    }

    private static boolean b(Activity activity) {
        Intent intent = activity.getIntent();
        if (intent.hasExtra("com.google.android.gms.ads.internal.purchase.useClientJar")) {
            return intent.getBooleanExtra("com.google.android.gms.ads.internal.purchase.useClientJar", false);
        }
        throw new a("InAppPurchaseManager requires the useClientJar flag in intent extras.");
    }

    private yu c(Activity activity) {
        try {
            return yv.a(a((Context) activity).a(og.a(activity)));
        } catch (RemoteException e) {
            adn.d("Could not create remote InAppPurchaseManager.", e);
            return null;
        } catch (g.a e2) {
            adn.d("Could not create remote InAppPurchaseManager.", e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.dynamic.g
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public yx b(IBinder iBinder) {
        return yy.a(iBinder);
    }
}
