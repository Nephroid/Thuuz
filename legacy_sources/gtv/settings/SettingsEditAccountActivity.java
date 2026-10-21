package com.thuuz.gtv.settings;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import com.thuuz.gtv.activity.AbstractSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bsq;
import defpackage.bsr;
import defpackage.bss;
import defpackage.bst;

/* loaded from: classes.dex */
public class SettingsEditAccountActivity extends AbstractSettings {
    private EditText f;
    private EditText g;
    private Button h;

    public SettingsEditAccountActivity() {
        super("Edit Account Info");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_edit_acct_layout);
        this.h = (Button) findViewById(bbq.save_profile_button);
        expandWidthByDevice(this.h);
        this.h.setOnClickListener(new bst(this));
        this.f = (EditText) findViewById(bbq.first);
        this.f.setText(j().bw());
        this.g = (EditText) findViewById(bbq.lastname);
        this.g.setText(j().bx());
        this.f.addTextChangedListener(new bss(this, this.h));
        this.g.addTextChangedListener(new bss(this, this.h));
        Button button = (Button) findViewById(bbq.change_pass);
        expandWidthByDevice(button);
        button.setOnClickListener(new bsr(this));
        Button button2 = (Button) findViewById(bbq.change_email);
        expandWidthByDevice(button2);
        button2.setOnClickListener(new bsq(this));
    }

    public boolean aF() {
        Editable text = this.f.getText();
        Editable text2 = this.g.getText();
        return text != null && text2 != null && text.toString().trim().length() > 0 && text2.toString().trim().length() > 0;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!j().i()) {
            e().info("finishing setting screen that now has no login");
            finish();
        }
        j().k("/prefs/account/edit");
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 1015:
            case 5260:
                if (i2 == -1) {
                    finish();
                    return;
                }
                return;
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#003";
    }
}
