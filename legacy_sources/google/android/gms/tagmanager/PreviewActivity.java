package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import defpackage.aru;
import defpackage.arv;
import defpackage.asj;

/* loaded from: classes.dex */
public class PreviewActivity extends Activity {
    private void a(String str, String str2, String str3) {
        AlertDialog create = new AlertDialog.Builder(this).create();
        create.setTitle(str);
        create.setMessage(str2);
        create.setButton(-1, str3, new aru(this));
        create.show();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            asj.c("Preview activity");
            Uri data = getIntent().getData();
            if (!arv.a(this).a(data)) {
                String str = "Cannot preview the app with the uri: " + data + ". Launching current version instead.";
                asj.b(str);
                a("Preview failure", str, "Continue");
            }
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if (launchIntentForPackage == null) {
                asj.c("No launch activity found for package name: " + getPackageName());
            } else {
                asj.c("Invoke the launch activity for package name: " + getPackageName());
                startActivity(launchIntentForPackage);
            }
        } catch (Exception e) {
            asj.a("Calling preview threw an exception: " + e.getMessage());
        }
    }
}
