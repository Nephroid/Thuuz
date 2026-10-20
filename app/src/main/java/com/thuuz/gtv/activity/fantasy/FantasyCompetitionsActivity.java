package com.thuuz.gtv.activity.fantasy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.WebViewFragment;
import defpackage.bbf;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bes;
import defpackage.bet;
import defpackage.beu;
import defpackage.bfx;
import defpackage.bpe;
import java.util.ArrayList;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class FantasyCompetitionsActivity extends AbstractActivity implements bfx {
    private final ArrayList<bpe> f;
    private bet g;
    private bes h;
    private Integer i;

    public FantasyCompetitionsActivity() {
        super("Fantasy Sports");
        this.f = new ArrayList<>();
        this.g = null;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.fantasy_competitions_activity);
        this.g = new bet(this, this);
        ListView listView = (ListView) findViewById(bbq.list);
        listView.setAdapter((ListAdapter) this.g);
        listView.setOnItemClickListener(new beu(this));
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        IService F = F();
        if (d() && F != null) {
            aF();
            if (this.h == null) {
                this.h = new bes(this);
                this.h.execute(new Void[0]);
            } else {
                e().info("skipping refresh because one already ongoing");
            }
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        if (F() != null) {
            c();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
        j().k("/fantasy/competitions");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Integer num) {
        a(num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void aF() {
        try {
            IService F = F();
            if (F != null) {
                this.f.clear();
                this.f.addAll(F.r());
                this.g.notifyDataSetChanged();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public Class<? extends AbstractActivity> P() {
        return FantasyActivity.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        e().info("got result " + i2 + " for req: " + i);
        if (i == 3300 && i2 == 1) {
            if (this.i == null) {
                e().warning("null comp id after signin");
            } else {
                c(this.i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public bbf au() {
        return new WebViewFragment(b("#022"));
    }
}
