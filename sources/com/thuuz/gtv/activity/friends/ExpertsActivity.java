package com.thuuz.gtv.activity.friends;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.AbstractSettings;
import com.thuuz.gtv.settings.SettingsActivity;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bhv;
import defpackage.bhw;
import defpackage.bhz;
import defpackage.bpc;
import defpackage.bqk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class ExpertsActivity extends AbstractSettings {
    private final List<bpc> f;
    private final Handler g;
    private final bqk h;
    private bhw i;
    private bhv j;
    private final View.OnClickListener k;
    private ListView l;

    public ExpertsActivity() {
        super("Experts");
        this.f = Collections.synchronizedList(new ArrayList());
        this.g = new Handler();
        this.h = new bqk(this.g);
        this.k = new bhz(this);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.experts_activity);
        this.l = (ListView) findViewById(bbq.list);
        a(this.l);
        this.j = new bhv(this, k());
        this.l.setAdapter((ListAdapter) this.j);
        this.l.setItemsCanFocus(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            this.h.a();
            this.j.clear();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            c();
            j().k("/friends/experts");
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        if (d()) {
            try {
                if (this.i != null) {
                    e().info("already refreshing; ignoring");
                } else {
                    this.i = new bhw(this);
                    this.i.execute(new Void[0]);
                }
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    public bqk aF() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aG() {
        this.j.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public Class<? extends AbstractActivity> P() {
        return SettingsActivity.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#012";
    }
}
