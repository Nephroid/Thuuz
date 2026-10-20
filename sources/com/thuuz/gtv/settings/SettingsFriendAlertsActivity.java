package com.thuuz.gtv.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.thuuz.gtv.activity.AbstractSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bte;
import defpackage.btf;
import defpackage.btg;
import defpackage.bth;
import defpackage.bti;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class SettingsFriendAlertsActivity extends AbstractSettings {
    private ListView f;
    private final List<FRIEND_ALERT_INDEX> g;
    private btg h;

    /* loaded from: classes.dex */
    public enum FRIEND_ALERT_INDEX {
        FRIEND_ALERT_INDEX_THUUZ_IT,
        FRIEND_ALERT_INDEX_FRIEND_FOLLOWS
    }

    public SettingsFriendAlertsActivity() {
        super("Friend Alerts");
        this.g = new ArrayList();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        bte bteVar = null;
        setContentView(bbr.settings_friend_alerts_activity);
        this.f = (ListView) findViewById(bbq.alerts_list);
        this.g.add(FRIEND_ALERT_INDEX.FRIEND_ALERT_INDEX_THUUZ_IT);
        this.g.add(FRIEND_ALERT_INDEX.FRIEND_ALERT_INDEX_FRIEND_FOLLOWS);
        this.f.setAdapter((ListAdapter) new btf(this, this));
        this.f.setOnItemClickListener(new bth(this, bteVar));
        this.h = new btg(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        j().k("/prefs/friend_alerts");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CheckBox checkBox, boolean z) {
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(z);
        checkBox.setOnCheckedChangeListener(this.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        bti btiVar;
        try {
            View childAt = this.f.getChildAt(i);
            if (childAt != null && (btiVar = (bti) childAt.getTag()) != null) {
                btiVar.c.toggle();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#008";
    }
}
