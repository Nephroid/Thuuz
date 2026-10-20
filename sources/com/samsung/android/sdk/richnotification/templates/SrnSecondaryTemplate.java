package com.samsung.android.sdk.richnotification.templates;

import android.graphics.Color;
import com.samsung.android.sdk.richnotification.SrnTemplate;
import defpackage.axf;
import defpackage.axg;

/* loaded from: classes.dex */
public abstract class SrnSecondaryTemplate extends SrnTemplate {

    @axf
    @axg(a = "background_color")
    private String mBackgroundColor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SrnSecondaryTemplate(String str, String str2) {
        super(SrnTemplate.Priority.SECONDARY, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SrnSecondaryTemplate(SrnSecondaryTemplate srnSecondaryTemplate) {
        super(srnSecondaryTemplate);
        this.mBackgroundColor = srnSecondaryTemplate.mBackgroundColor;
    }

    public void setBackgroundColor(int i) {
        this.mBackgroundColor = String.format("%2X,%2X,%2X", Integer.valueOf(Color.red(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.blue(i)));
    }
}
