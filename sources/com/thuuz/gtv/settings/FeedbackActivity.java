package com.thuuz.gtv.settings;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.thuuz.gtv.activity.AbstractSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.brc;

/* loaded from: classes.dex */
public class FeedbackActivity extends AbstractSettings {
    private EditText f;
    private EditText g;
    private EditText h;
    private Button i;
    private LinearLayout j;

    public FeedbackActivity() {
        super("Feedback");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.feedback_layout);
        this.f = (EditText) findViewById(bbq.name);
        this.f.setText(j().bY());
        this.g = (EditText) findViewById(bbq.email);
        this.g.setText(j().by());
        this.h = (EditText) findViewById(bbq.feedback);
        this.i = (Button) findViewById(bbq.submit_feedback);
        expandWidthByDevice(this.i);
        this.j = (LinearLayout) findViewById(bbq.gtv_feedback_layout);
        if (ag()) {
            this.j.setVisibility(8);
            this.i.setText(getString(bbt.send_feedback));
        } else {
            this.j.setVisibility(0);
        }
        this.i.setOnClickListener(new brc(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
        j().k("/prefs/feedback");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        if (this.j.isShown()) {
            ((EditText) findViewById(bbq.name)).requestFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Intent intent) {
        try {
            k().startActivity(Intent.createChooser(intent, getResources().getString(bbt.send_feedback)));
            j().a("feedback", "email", "", 0L);
        } catch (ActivityNotFoundException e) {
            e().warning("Emailer activity NOT found!");
            c("Unable to Send", "No email app was found.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#002";
    }
}
