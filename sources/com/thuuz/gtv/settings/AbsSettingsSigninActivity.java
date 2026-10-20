package com.thuuz.gtv.settings;

import android.content.Intent;
import com.thuuz.gtv.activity.AbstractSettings;

/* loaded from: classes.dex */
public abstract class AbsSettingsSigninActivity extends AbstractSettings {
    /* JADX INFO: Access modifiers changed from: protected */
    public AbsSettingsSigninActivity(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 351:
                if (j().i()) {
                    finish();
                    return;
                }
                return;
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }
}
