package com.samsung.android.sdk.richnotification.templates;

import com.samsung.android.sdk.richnotification.SrnImageAsset;
import com.samsung.android.sdk.richnotification.Utilities;
import defpackage.axf;
import defpackage.axg;

/* loaded from: classes.dex */
public final class SrnQRTemplate extends SrnPrimaryTemplate {
    private static final String TEMPLATE_NAME = "qr_template";

    @axf
    @axg(a = Utilities.DB_KEY_IMAGE)
    private SrnImageAsset mImage;

    @axf
    @axg(a = "sub_header")
    private String mSubHeader;

    public SrnQRTemplate() {
        super(TEMPLATE_NAME, "additional_template");
    }

    private SrnQRTemplate(SrnQRTemplate srnQRTemplate) {
        super(srnQRTemplate);
        this.mSubHeader = srnQRTemplate.mSubHeader;
        this.mImage = srnQRTemplate.mImage;
    }

    public void setSubHeader(String str) {
        this.mSubHeader = str;
    }

    public void setImage(SrnImageAsset srnImageAsset) {
        this.mImage = srnImageAsset;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.richnotification.SrnTemplate
    public Object cloneSelf() {
        return new SrnQRTemplate(this);
    }
}
