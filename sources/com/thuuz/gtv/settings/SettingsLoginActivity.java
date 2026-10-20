package com.thuuz.gtv.settings;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thuuz.gtv.activity.AbstractFbSettings;
import com.thuuz.gtv.util.ServerChoice;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.bqg;
import defpackage.bts;
import defpackage.btt;
import defpackage.btu;
import defpackage.btv;
import defpackage.btw;
import defpackage.btx;
import defpackage.bty;
import defpackage.btz;
import defpackage.bub;
import defpackage.buc;
import java.net.URLEncoder;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsLoginActivity extends AbstractFbSettings {
    private EditText f;
    private EditText g;
    private TextView h;
    private Button i;

    public SettingsLoginActivity() {
        super("Sign In");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        String str;
        bts btsVar = null;
        setContentView(bbr.settings_login_activity);
        this.h = (TextView) findViewById(bbq.error_pass);
        this.f = (EditText) findViewById(bbq.email);
        if (aB()) {
            getWindow().setSoftInputMode(5);
        }
        TextView textView = (TextView) findViewById(bbq.forgot_pwd);
        this.g = (EditText) findViewById(bbq.pass);
        this.i = (Button) findViewById(bbq.login_button);
        expandWidthByDevice(this.i);
        LinearLayout linearLayout = (LinearLayout) findViewById(bbq.err_layout);
        textView.setOnClickListener(new bts(this));
        textView.setOnKeyListener(new btt(this));
        this.f.setVisibility(0);
        this.f.addTextChangedListener(new buc(this, btsVar));
        this.g.setVisibility(0);
        this.g.addTextChangedListener(new buc(this, btsVar));
        if (getIntent().hasExtra("email")) {
            Bundle extras = getIntent().getExtras();
            str = extras != null ? extras.getString("email") : null;
            e().info("extra has email: " + str);
        } else if (bundle != null) {
            try {
                str = bundle.getString("email");
            } catch (Throwable th) {
                str = null;
            }
        } else {
            str = null;
        }
        if (str == null && (((str = j().by()) == null || str.length() == 0) && j().aU() != null)) {
            str = j().aU();
        }
        if (str == null) {
            String N = j().N();
            if (N.length() > 0) {
                str = N + "@gmail.com";
            }
        }
        this.f.setText(str);
        this.i.setOnClickListener(new btz(this, btsVar));
        linearLayout.setVisibility(0);
        ((CheckBox) findViewById(bbq.mask)).setOnCheckedChangeListener(new btu(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractFbSettings, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        Bundle extras;
        super.onSaveInstanceState(bundle);
        if (getIntent().hasExtra("email") && (extras = getIntent().getExtras()) != null) {
            bundle.putString("email", extras.getString("email"));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractFbSettings, com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        j().k("/prefs/account/signin");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aK() {
        try {
            StringBuilder sb = new StringBuilder(ServerChoice.q);
            sb.append("forgot_password/");
            Editable text = this.f.getText();
            if (text != null) {
                String trim = text.toString().trim();
                if (trim.length() > 0) {
                    sb.append("?email=");
                    sb.append(URLEncoder.encode(trim, "utf-8"));
                }
            }
            e(sb.toString(), "Recover Password");
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean aL() {
        Editable text = this.f.getText();
        return text != null && this.g.getText() != null && text.toString().trim().length() > 0 && this.g.getText().toString().length() > 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractFbSettings, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 511:
                if (i2 == 1) {
                    setResult(1);
                    finish();
                    return;
                }
                return;
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aM() {
        if (j().aY().a()) {
            if (j().ba()) {
                h(true);
                return;
            } else {
                aN();
                return;
            }
        }
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirm with Facebook?").setCancelable(true).setMessage("Your account has Facebook connected. Confirm with Facebook now?");
            AlertDialog create = builder.create();
            create.setButton(-2, "No, disconnect", new btv(this, create));
            create.setButton(-1, "Yes, confirm", new btw(this, create));
            create.setOnCancelListener(new btx(this));
            a(create);
            create.show();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Problem with alert: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(boolean z) {
        try {
            new bty(this, z).execute(new Void[0]);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Problem with alert: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aN() {
        if (d()) {
            d(getString(bbt.welcome_back), "");
        }
        setResult(1);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#003";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractFbSettings
    public bqg aF() {
        return new bub(this);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void ad() {
        if (d()) {
            b("Failed to Associate Fb", bbt.pls_try_again_later);
        }
        aN();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void ac() {
        e().info("got associate, finishing...");
        aN();
    }
}
