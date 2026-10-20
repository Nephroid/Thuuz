package com.thuuz.gtv.activity.nobar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class FromCustomUrlActivity extends AbstractNoActionBarActivity {
    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        aG();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        IService F;
        try {
            F = F();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
            aF();
        }
        if (F == null) {
            aF();
            return;
        }
        if (getIntent() == null || !"android.intent.action.VIEW".equals(getIntent().getAction())) {
            aF();
            return;
        }
        Uri data = getIntent().getData();
        if (data == null) {
            aF();
            return;
        }
        String scheme = data.getScheme();
        if (scheme == null) {
            aF();
            return;
        }
        if (!scheme.contains("thuuz") && !scheme.contains("tt")) {
            aF();
            return;
        }
        String substring = data.toString().substring(scheme.length() + "://".length());
        if (substring.length() == 0) {
            aF();
            return;
        }
        String str = substring.split("/")[0];
        if (str.contains("?")) {
            str = str.split("\\?")[0];
        }
        if (str == null || str.length() == 0) {
            aF();
            return;
        }
        e().info("processing host: " + str + ", hostpath: " + substring);
        if (str.equals("auth_fail")) {
            b(F);
            if (j().i()) {
                d("Authentication Ok", "Sign-in was already successful");
            } else {
                v();
            }
            finish();
            return;
        }
        if (str.equals("game")) {
            try {
                if (substring.split("/").length < 2) {
                    aF();
                } else {
                    a(F, substring);
                    finish();
                }
                return;
            } catch (Throwable th2) {
                e().log(Level.SEVERE, "Cannot handle game path: " + substring, th2);
                aF();
                return;
            }
        }
        if (str.equals("closeme")) {
            aF();
            return;
        }
        try {
            if (new ThuuzWebViewClient(this).shouldOverrideUrlLoading((WebView) null, data.toString())) {
                finish();
                return;
            }
        } catch (Throwable th3) {
            e().log(Level.WARNING, "problem with off-label usage of ThuuzWebViewClient: ", th3);
        }
        if (!isFinishing()) {
            aF();
            return;
        }
        return;
        e().log(Level.SEVERE, "problem: ", th);
        aF();
    }

    private void aF() {
        e().warning("problem launching thuuz incoming url: " + getIntent());
        b(F());
        finish();
    }

    private void b(IService iService) {
        Intent b = j().b((Activity) this);
        if (iService.a(j().aF())) {
            b.setFlags(131072);
        }
        startActivity(b);
    }

    private void aG() {
        Intent p = p();
        p.putExtra("SPIN_ONLY", Boolean.TRUE);
        startActivity(p);
    }
}
