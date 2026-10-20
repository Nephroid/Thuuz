package com.thuuz.gtv.settings;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.buj;
import defpackage.bum;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsRegisterActivity extends AbsSettingsSigninActivity {
    private EditText f;
    private EditText g;
    private EditText h;
    private EditText i;
    private Button j;

    public SettingsRegisterActivity() {
        super("Register with Email");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.settings_register_layout);
        this.j = (Button) findViewById(bbq.register_button);
        this.j.setOnClickListener(new bum(this));
        expandWidthByDevice(this.j);
        this.h = (EditText) findViewById(bbq.first);
        this.i = (EditText) findViewById(bbq.lastname);
        this.f = (EditText) findViewById(bbq.email);
        this.g = (EditText) findViewById(bbq.pass);
        this.h.addTextChangedListener(new buj(this, this.j));
        this.h.setText(j().bw());
        this.i.addTextChangedListener(new buj(this, this.j));
        this.i.setText(j().bx());
        this.f.addTextChangedListener(new buj(this, this.j));
        this.f.setText(j().by());
        this.g.addTextChangedListener(new buj(this, this.j));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        j().k("/prefs/account/register");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean aF() {
        try {
            Editable text = this.f.getText();
            Editable text2 = this.g.getText();
            Editable text3 = this.h.getText();
            Editable text4 = this.i.getText();
            if (text == null || text2 == null || text3 == null || text4 == null) {
                return true;
            }
            if (this.h.getText().toString().trim().length() > 0 && this.i.getText().toString().trim().length() > 0 && this.f.getText().toString().trim().length() > 0) {
                if (this.g.getText().toString().length() > 0) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#003";
    }
}
