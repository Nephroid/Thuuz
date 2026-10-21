package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.g;
import defpackage.adn;
import defpackage.og;
import defpackage.xu;
import defpackage.xv;
import defpackage.xx;
import defpackage.xy;
import defpackage.zy;

@zy
/* loaded from: classes.dex */
public final class dr extends g<xx> {
    private static final dr a = new dr();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    private dr() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    public static xu a(Activity activity) {
        xu c;
        try {
            if (b(activity)) {
                adn.a("Using AdOverlay from the client jar.");
                c = new dk(activity);
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
        if (intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            return intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
        }
        throw new a("Ad overlay requires the useClientJar flag in intent extras.");
    }

    private xu c(Activity activity) {
        try {
            return xv.a(a((Context) activity).a(og.a(activity)));
        } catch (RemoteException e) {
            adn.d("Could not create remote AdOverlay.", e);
            return null;
        } catch (g.a e2) {
            adn.d("Could not create remote AdOverlay.", e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.dynamic.g
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public xx b(IBinder iBinder) {
        return xy.a(iBinder);
    }
}
