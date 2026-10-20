package com.thuuz.gtv.settings;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
// [removed legacy tracker import]
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bpw;
import defpackage.bqg;
import defpackage.bun;
import defpackage.buo;
import defpackage.bup;
import defpackage.buq;
import defpackage.cag;
import defpackage.caj;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsSocialRegisterActivity extends AbsSettingsSigninActivity implements caj {
    private ImageView f;
    private EditText g;
    private EditText h;
    private EditText i;
    private EditText j;
    private AsyncTask<Void, Void, Bitmap> k;
    private Button l;

    public SettingsSocialRegisterActivity() {
        super("Sign Up with Facebook");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_social_register_layout);
        this.l = (Button) findViewById(bbq.register_button);
        expandWidthByDevice(this.l);
        this.l.setOnClickListener(new buq(this, null));
        this.f = (ImageView) findViewById(bbq.profile_pic);
        this.g = (EditText) findViewById(bbq.first_name);
        this.h = (EditText) findViewById(bbq.last_name);
        this.i = (EditText) findViewById(bbq.email);
        this.j = (EditText) findViewById(bbq.pass);
        this.g.addTextChangedListener(new bup(this, this.l));
        this.h.addTextChangedListener(new bup(this, this.l));
        this.i.addTextChangedListener(new bup(this, this.l));
        this.j.addTextChangedListener(new bup(this, this.l));
        ((CheckBox) findViewById(bbq.mask)).setOnCheckedChangeListener(new bun(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            if (this.k != null) {
                this.k.cancel(true);
                this.k = null;
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        super.onDestroy();
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        try {
            Session activeSession = Session.getActiveSession();
            if (activeSession == null || !activeSession.isOpened()) {
                e().severe("finishing social reg because not fb authenticated");
                finish();
                return;
            }
            bpw c = j().aY().c();
            if (c != null) {
                a(c);
            } else {
                new bqg(this).a(Session.getActiveSession());
            }
            j().k("/prefs/account/register/social");
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void a(bpw bpwVar) {
        if (bpwVar != null) {
            try {
                this.g.setText(bpwVar.b());
                this.h.setText(bpwVar.c());
                this.i.setText(bpwVar.d());
                this.f.setTag(bpwVar.e());
                this.k = new cag(this, bpwVar.e());
                this.k.execute(new Void[0]);
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean aF() {
        return this.i.getText() != null && this.j.getText() != null && this.g.getText() != null && this.h.getText() != null && this.g.getText().toString().trim().length() > 0 && this.h.getText().toString().trim().length() > 0 && b.matcher(this.i.getText().toString().trim()).matches() && this.j.getText().toString().length() >= 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aG() {
        new buo(this).execute(new Void[0]);
    }

    @Override // defpackage.caj
    public void a(Bitmap bitmap) {
        if (!isDestroyed()) {
            this.f.setImageBitmap(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#003";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }
}
