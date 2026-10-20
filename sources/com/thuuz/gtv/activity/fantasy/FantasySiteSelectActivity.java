package com.thuuz.gtv.activity.fantasy;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.FantasySite;
import com.thuuz.gtv.settings.AbstractLoginRequiredSettingsActivity;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bfi;
import defpackage.bfj;
import defpackage.bfk;
import defpackage.bpe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class FantasySiteSelectActivity extends AbstractLoginRequiredSettingsActivity {
    private final List<FantasySite> f;
    private bfj g;
    private int h;
    private bfk i;

    public FantasySiteSelectActivity() {
        super("Import Teams");
        this.f = new ArrayList();
        this.h = -1;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.fantasy_site_select_activity);
        this.h = getIntent().getIntExtra("competition_id", -1);
        if (this.h == -1 && bundle != null) {
            this.h = bundle.getInt("competition_id", -1);
        }
        if (this.h == -1) {
            e().info("no competition_id for fantasy sites. bailing.");
            finish();
        } else {
            ListView listView = (ListView) findViewById(bbq.list);
            this.g = new bfj(this, this);
            listView.setAdapter((ListAdapter) this.g);
            listView.setOnItemClickListener(new bfi(this, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("competition_id", this.h);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        if (F() != null) {
            c();
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        IService F = F();
        if (d() && F != null) {
            bpe j = F.j(this.h);
            if (j != null) {
                a("Import Teams from " + j.b());
            }
            aF();
            if (this.i == null) {
                this.i = new bfk(this, null);
                this.i.execute(new Void[0]);
            } else {
                e().info("skipping refresh because one is ongoing");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
        j().k("/fantasy/import/account");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void aF() {
        try {
            if (F() != null) {
                Collections.sort(this.f);
                this.g.notifyDataSetChanged();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public Class<? extends AbstractActivity> P() {
        return FantasyActivity.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#022";
    }
}
