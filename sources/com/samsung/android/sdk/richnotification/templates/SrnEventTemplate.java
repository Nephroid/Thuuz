package com.samsung.android.sdk.richnotification.templates;

import defpackage.axf;
import defpackage.axg;

/* loaded from: classes.dex */
final class SrnEventTemplate extends SrnPrimaryTemplate {
    private static final String TEMPLATE_NAME = "event_template";

    @axf
    @axg(a = "large_string")
    private String mLargeString;

    @axf
    @axg(a = "sub_header")
    private String mSubHeader;

    @axf
    @axg(a = "sub_script_string")
    private String mSubscriptString;

    public SrnEventTemplate() {
        super(TEMPLATE_NAME, "additional_template");
    }

    private SrnEventTemplate(SrnEventTemplate srnEventTemplate) {
        super(srnEventTemplate);
        this.mLargeString = srnEventTemplate.mLargeString;
        this.mSubscriptString = srnEventTemplate.mSubscriptString;
        this.mSubHeader = srnEventTemplate.mSubHeader;
    }

    public void setLargeString(String str) {
        this.mLargeString = str;
    }

    public void setSubscriptString(String str) {
        this.mSubscriptString = str;
    }

    public void setSubHeader(String str) {
        this.mSubHeader = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.richnotification.SrnTemplate
    public Object cloneSelf() {
        return new SrnEventTemplate(this);
    }
}
