package com.thuuz.gtv.settings;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.thuuz.gtv.activity.AbstractSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bsl;
import defpackage.bsm;
import defpackage.bsn;

/* loaded from: classes.dex */
public class SettingsChangePassActivity extends AbstractSettings {
    private EditText f;
    private EditText g;
    private EditText h;

    public SettingsChangePassActivity() {
        super("Change Password");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_change_pass_layout);
        Button button = (Button) findViewById(bbq.change_pass_button);
        expandWidthByDevice(button);
        button.setOnClickListener(new bsn(this, null));
        button.setEnabled(false);
        this.f = (EditText) findViewById(bbq.old);
        this.g = (EditText) findViewById(bbq.newpass);
        this.h = (EditText) findViewById(bbq.confirm);
        this.f.addTextChangedListener(new bsm(this, button));
        this.g.addTextChangedListener(new bsm(this, button));
        this.h.addTextChangedListener(new bsm(this, button));
        ((CheckBox) findViewById(bbq.mask)).setOnCheckedChangeListener(new bsl(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!j().i()) {
            e().info("finishing setting screen that now has no login");
            finish();
        }
        j().k("/prefs/account/change_pass");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean aF() {
        Editable text = this.h.getText();
        Editable text2 = this.g.getText();
        Editable text3 = this.f.getText();
        if (text == null || text2 == null || text3 == null) {
            return false;
        }
        return text3.toString().trim().length() > 0 && text2.toString().trim().length() > 0 && text.toString().trim().length() > 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#003";
    }
}
