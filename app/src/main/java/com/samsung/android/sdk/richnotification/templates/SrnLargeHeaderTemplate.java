package com.samsung.android.sdk.richnotification.templates;

/* loaded from: classes.dex */
public final class SrnLargeHeaderTemplate extends SrnPrimaryTemplate {
    private static final String TEMPLATE_NAME = "large_header_template";

    public SrnLargeHeaderTemplate() {
        super(TEMPLATE_NAME, "default");
    }

    private SrnLargeHeaderTemplate(SrnLargeHeaderTemplate srnLargeHeaderTemplate) {
        super(srnLargeHeaderTemplate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.richnotification.SrnTemplate
    public Object cloneSelf() {
        return new SrnLargeHeaderTemplate(this);
    }
}
