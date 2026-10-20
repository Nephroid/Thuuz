package com.thuuz.gtv.settings;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.thuuz.gtv.activity.AbstractSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bsh;
import defpackage.bsi;
import defpackage.bsj;

/* loaded from: classes.dex */
public class SettingsChangeEmailActivity extends AbstractSettings {
    private EditText f;
    private EditText g;

    public SettingsChangeEmailActivity() {
        super("Change Email");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_change_email_layout);
        Button button = (Button) findViewById(bbq.change_email_button);
        expandWidthByDevice(button);
        button.setOnClickListener(new bsj(this, null));
        button.setEnabled(false);
        this.g = (EditText) findViewById(bbq.email);
        this.f = (EditText) findViewById(bbq.pass);
        this.g.addTextChangedListener(new bsi(this, button));
        this.f.addTextChangedListener(new bsi(this, button));
        ((CheckBox) findViewById(bbq.mask)).setOnCheckedChangeListener(new bsh(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean aF() {
        Editable text = this.g.getText();
        if (text != null) {
            return this.f.length() >= 6 && b.matcher(text.toString().trim()).matches();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!j().i()) {
            e().info("finishing setting screen that now has no login");
            finish();
        }
        j().k("/prefs/account/change_email");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#003";
    }
}
