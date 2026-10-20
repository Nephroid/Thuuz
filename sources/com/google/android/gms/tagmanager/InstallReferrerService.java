package com.google.android.gms.tagmanager;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.CampaignTrackingService;
import defpackage.asi;

/* loaded from: classes.dex */
public final class InstallReferrerService extends IntentService {
    CampaignTrackingService a;
    Context b;

    public InstallReferrerService() {
        super("InstallReferrerService");
    }

    private void a(Context context, Intent intent) {
        if (this.a == null) {
            this.a = new CampaignTrackingService();
        }
        this.a.a(context, intent);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        String stringExtra = intent.getStringExtra("referrer");
        Context applicationContext = this.b != null ? this.b : getApplicationContext();
        asi.a(applicationContext, stringExtra);
        a(applicationContext, intent);
    }
}
