package com.thuuz.gtv.activity.friends;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.AbstractSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bin;
import defpackage.bio;
import defpackage.bip;
import defpackage.biq;
import defpackage.bir;
import defpackage.bit;
import defpackage.bqb;
import defpackage.bqd;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SearchFriendsActivity extends AbstractSettings {
    private static final Integer f = 1;
    private static final Integer g = 2;
    private LinearLayout h;
    private EditText i;
    private LinearLayout j;
    private TextView k;
    private TextView l;
    private Button m;
    private Button n;
    private bit o;
    private bqb p;
    private bir q;
    private boolean r;

    public SearchFriendsActivity() {
        super("Search all Thuuzers");
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = true;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.search_friends_activity);
        this.h = (LinearLayout) findViewById(bbq.searchForm);
        this.i = (EditText) findViewById(bbq.email);
        this.j = (LinearLayout) findViewById(bbq.searchResults);
        this.k = (TextView) findViewById(bbq.resultsTitle);
        this.l = (TextView) findViewById(bbq.resultsEmail);
        this.m = (Button) findViewById(bbq.leftButton);
        expandWidthByDevice(this.m);
        this.n = (Button) findViewById(bbq.rightButton);
        expandWidthByDevice(this.n);
        aN();
        ((Button) findViewById(bbq.search)).setOnClickListener(new bin(this));
        this.m.setOnClickListener(new bio(this));
        this.n.setOnClickListener(new bip(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            if (this.r) {
                this.r = false;
                this.i.requestFocus();
                aa().postDelayed(new biq(this), 100L);
            }
            j().k("/friends/search");
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aK() {
        if (this.q != null) {
            e().info("follow in progress; ignoring");
        } else if (new bqd(j().bj()).a().compareTo(String.format("%d", Integer.valueOf(this.p.a()))) == 0) {
            c("Follow error", "Cannot follow self");
        } else {
            this.q = new bir(this, this.p.a());
            this.q.execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aL() {
        if (this.o != null) {
            e().info("already searching; ignoring");
            return;
        }
        String aM = aM();
        this.i.setText(aM);
        if (!b.matcher(aM).matches()) {
            c("Invalid Entry", "Please enter a valid email address");
        } else {
            this.o = new bit(this, aM);
            this.o.execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String aM() {
        Editable text = this.i.getText();
        return text == null ? "" : text.toString().trim();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aN() {
        h(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(boolean z) {
        this.h.setVisibility(z ? 8 : 0);
        this.j.setVisibility(z ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public Class<? extends AbstractActivity> P() {
        return FriendsActivity.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#008";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }
}
