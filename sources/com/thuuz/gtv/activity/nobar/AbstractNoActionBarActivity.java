package com.thuuz.gtv.activity.nobar;

import android.os.Bundle;
import com.thuuz.gtv.activity.AbstractActivity;

/* loaded from: classes.dex */
public abstract class AbstractNoActionBarActivity extends AbstractActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractNoActionBarActivity() {
        super("");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.app.Activity
    public void setContentView(int i) {
        super.setContentView(i);
        if (getSherlock() != null && getSherlock().getActionBar() != null) {
            getSherlock().getActionBar().hide();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void K() {
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean o() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean t() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a() {
    }
}
