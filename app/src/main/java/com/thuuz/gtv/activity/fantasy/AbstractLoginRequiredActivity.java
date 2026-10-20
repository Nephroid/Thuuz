package com.thuuz.gtv.activity.fantasy;

import android.os.Bundle;
import com.thuuz.gtv.activity.AbstractActivity;

/* loaded from: classes.dex */
public abstract class AbstractLoginRequiredActivity extends AbstractActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractLoginRequiredActivity(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!j().i()) {
            e().info("quiting because login required");
            finish();
        }
    }
}
