package com.samsung.android.sdk.richnotification.templates;

import com.samsung.android.sdk.richnotification.SrnImageAsset;
import com.samsung.android.sdk.richnotification.Utilities;
import defpackage.axf;
import defpackage.axg;

/* loaded from: classes.dex */
public final class SrnStandardSecondaryTemplate extends SrnSecondaryTemplate {
    private static final String TEMPLATE_NAME = "template_secondary";

    @axf
    @axg(a = "body")
    private String mBody;

    @axf
    @axg(a = Utilities.DB_KEY_IMAGE)
    private SrnImageAsset mImage;

    @axf
    @axg(a = "small_icon")
    private SrnImageAsset mSmallIcon;

    @axf
    @axg(a = "small_icon2")
    private SrnImageAsset mSmallIcon2;

    @axf
    @axg(a = "small_icon_title")
    private String mSmallIconTitle;

    @axf
    @axg(a = "small_icon_title2")
    private String mSmallIconTitle2;

    @axf
    @axg(a = "sub_header")
    private String mSubHeader;

    @axf
    @axg(a = "title")
    private String mTitle;

    public SrnStandardSecondaryTemplate() {
        super(TEMPLATE_NAME, "default");
    }

    SrnStandardSecondaryTemplate(SrnStandardSecondaryTemplate srnStandardSecondaryTemplate) {
        super(srnStandardSecondaryTemplate);
        this.mImage = srnStandardSecondaryTemplate.mImage;
        this.mTitle = srnStandardSecondaryTemplate.mTitle;
        this.mSubHeader = srnStandardSecondaryTemplate.mSubHeader;
        this.mBody = srnStandardSecondaryTemplate.mBody;
        this.mSmallIcon = srnStandardSecondaryTemplate.mSmallIcon;
        this.mSmallIconTitle = srnStandardSecondaryTemplate.mSmallIconTitle;
        this.mSmallIcon2 = srnStandardSecondaryTemplate.mSmallIcon2;
        this.mSmallIconTitle2 = srnStandardSecondaryTemplate.mSmallIconTitle2;
    }

    public void setImage(SrnImageAsset srnImageAsset) {
        this.mImage = srnImageAsset;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setSubHeader(String str) {
        this.mSubHeader = str;
    }

    public void setBody(String str) {
        this.mBody = str;
    }

    public void setSmallIcon1(SrnImageAsset srnImageAsset, String str) {
        this.mSmallIcon = srnImageAsset;
        this.mSmallIconTitle = str;
    }

    public void setSmallIcon2(SrnImageAsset srnImageAsset, String str) {
        this.mSmallIcon2 = srnImageAsset;
        this.mSmallIconTitle2 = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.richnotification.SrnTemplate
    public Object cloneSelf() {
        return new SrnStandardSecondaryTemplate(this);
    }
}
