package com.thuuz.gtv.settings;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.view.Menu;
import com.facebook.UiLifecycleHelper;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bud;
import defpackage.bue;
import defpackage.buf;
import defpackage.buh;
import defpackage.cag;
import defpackage.caj;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsMaintAccountActivity extends AbsSettingsSigninActivity implements caj {
    private TextView f;
    private TextView g;
    private ImageView h;
    private Button i;
    private Button j;
    private UiLifecycleHelper k;
    private buh l;

    public SettingsMaintAccountActivity() {
        super("Account Info");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        bud budVar = null;
        setContentView(bbr.settings_maint_layout);
        this.h = (ImageView) findViewById(bbq.pic);
        aG();
        this.f = (TextView) findViewById(bbq.email);
        this.g = (TextView) findViewById(bbq.signed_in_as);
        this.i = (Button) findViewById(bbq.edit_info);
        expandWidthByDevice(this.i);
        this.i.setOnClickListener(new bue(this, budVar));
        aF();
        this.j = (Button) findViewById(bbq.signout_button);
        expandWidthByDevice(this.j);
        this.j.setOnClickListener(new buf(this, budVar));
        this.l = new buh(this);
        this.k = new UiLifecycleHelper(this, this.l);
        this.k.onCreate(bundle);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            this.k.onResume();
            c();
            j().k("/prefs/account/maint");
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            this.k.onDestroy();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        super.onDestroy();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        try {
            this.k.onPause();
            super.onPause();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            this.k.onSaveInstanceState(bundle);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.settings.AbsSettingsSigninActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            this.k.onActivityResult(i, i2, intent);
            super.onActivityResult(i, i2, intent);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        try {
            if (!j().i()) {
                e().info("finishing acct. main screen that now has no login");
                finish();
            }
            this.f.setText(j().by());
            this.g.setText(j().bY());
            aF();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aF() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aG() {
        this.h.setTag(j().bb());
        new cag(this, j().bb()).execute(new Void[0]);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void ac() {
        runOnUiThread(new bud(this));
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void ad() {
    }

    @Override // defpackage.caj
    public void a(Bitmap bitmap) {
        if (!isDestroyed()) {
            this.h.setImageBitmap(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#003";
    }
}
