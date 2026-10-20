package com.thuuz.gtv.activity.sports;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.actionbarsherlock.app.ActionBar;
import com.facebook.internal.ServerProtocol;
import com.thuuz.gtv.activity.AbstractActivity;
import defpackage.bbp;
import defpackage.bbq;
import defpackage.bmk;
import java.util.logging.Level;

/* loaded from: classes.dex */
public abstract class AbstractTabActivity extends AbstractActivity {
    protected BroadcastReceiver g;

    public abstract void e(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        aG();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractTabActivity(String str) {
        super(str);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            if (this.g != null) {
                unregisterReceiver(this.g);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void O() {
        try {
            if (ax()) {
                aw();
            } else {
                av();
                j().b(true);
                j().a("sports", ServerProtocol.DIALOG_PARAM_DISPLAY, "drawer", 0L);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    protected void aG() {
        try {
            if (this.g == null) {
                e().info("registering fantasy listener");
                IntentFilter intentFilter = new IntentFilter("fantasy_sync");
                this.g = new bmk(this);
                registerReceiver(this.g, intentFilter, "com.thuuz.permission.local.broadcast", null);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        try {
            if (ax()) {
                e().info("closing drawer from back button");
                aw();
            } else {
                super.onBackPressed();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem w/ back in " + getClass().getSimpleName() + ", finishing: ", th);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void L() {
        View customView;
        ImageView imageView;
        super.L();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null && (customView = supportActionBar.getCustomView()) != null && (imageView = (ImageView) customView.findViewById(bbq.up)) != null) {
            imageView.setImageResource(bbp.ic_drawer);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean ab() {
        return true;
    }
}
