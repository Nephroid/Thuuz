package com.samsung.android.sdk.richnotification.templates;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.samsung.android.sdk.richnotification.SrnImageAsset;
import com.samsung.android.sdk.richnotification.SrnTemplate;
import defpackage.axf;
import defpackage.axg;

/* loaded from: classes.dex */
public abstract class SrnPrimaryTemplate extends SrnTemplate {

    @axf
    @axg(a = "background_color")
    private String mBackgroundColor;

    @axf
    @axg(a = "background_image")
    private SrnImageAsset mBackgroundImage;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SrnPrimaryTemplate(String str, String str2) {
        super(SrnTemplate.Priority.PRIMARY, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SrnPrimaryTemplate(SrnPrimaryTemplate srnPrimaryTemplate) {
        super(srnPrimaryTemplate);
        this.mBackgroundImage = srnPrimaryTemplate.mBackgroundImage;
        this.mBackgroundColor = srnPrimaryTemplate.mBackgroundColor;
    }

    public void setBackgroundImage(SrnImageAsset srnImageAsset) {
        this.mBackgroundImage = srnImageAsset;
        if (this.mBackgroundImage != null) {
            TemplateUtil.setCompressFormat(this.mBackgroundImage, Bitmap.CompressFormat.JPEG);
        }
    }

    public void setBackgroundColor(int i) {
        this.mBackgroundColor = String.format("%2X,%2X,%2X", Integer.valueOf(Color.red(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.blue(i)));
    }
}
