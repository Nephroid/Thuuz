package com.google.android.gms.analytics;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import defpackage.gn;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class CampaignTrackingService extends IntentService {
    public CampaignTrackingService() {
        super("CampaignIntentService");
    }

    public void a(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("referrer");
        try {
            FileOutputStream openFileOutput = context.openFileOutput("gaInstallData", 0);
            openFileOutput.write(stringExtra.getBytes());
            openFileOutput.close();
            gn.c("Stored campaign information.");
        } catch (IOException e) {
            gn.a("Error storing install campaign.");
        }
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent intent) {
        a(this, intent);
    }
}
