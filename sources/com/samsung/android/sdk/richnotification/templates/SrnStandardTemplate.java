package com.samsung.android.sdk.richnotification.templates;

import defpackage.axf;
import defpackage.axg;

/* loaded from: classes.dex */
public final class SrnStandardTemplate extends SrnPrimaryTemplate {
    private static final String FULL_TEMPLATE_NAME = "full_screen_template";
    private static final String MEIDUM_TEMPLATE_NAME = "medium_header_template";
    private static final String SMALL_TEMPLATE_NAME = "small_header_template";

    @axf
    @axg(a = "body2")
    private String mBody;

    @axf
    @axg(a = "sub_header")
    private String mSubHeader;

    /* loaded from: classes.dex */
    public enum HeaderSizeType {
        FULL_SCREEN(SrnStandardTemplate.FULL_TEMPLATE_NAME),
        MEDIUM(SrnStandardTemplate.MEIDUM_TEMPLATE_NAME),
        SMALL(SrnStandardTemplate.SMALL_TEMPLATE_NAME);

        private final String mTemplateName;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static HeaderSizeType[] valuesCustom() {
            HeaderSizeType[] valuesCustom = values();
            int length = valuesCustom.length;
            HeaderSizeType[] headerSizeTypeArr = new HeaderSizeType[length];
            System.arraycopy(valuesCustom, 0, headerSizeTypeArr, 0, length);
            return headerSizeTypeArr;
        }

        HeaderSizeType(String str) {
            this.mTemplateName = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getTemplateName() {
            return this.mTemplateName;
        }
    }

    public SrnStandardTemplate() {
        this(HeaderSizeType.SMALL);
    }

    private SrnStandardTemplate(SrnStandardTemplate srnStandardTemplate) {
        super(srnStandardTemplate);
        this.mSubHeader = srnStandardTemplate.mSubHeader;
        this.mBody = srnStandardTemplate.mBody;
    }

    public SrnStandardTemplate(HeaderSizeType headerSizeType) {
        super(headerSizeType.getTemplateName(), "default");
    }

    public void setSubHeader(String str) {
        this.mSubHeader = str;
    }

    public void setBody(String str) {
        this.mBody = str;
    }

    public void setHeaderSize(HeaderSizeType headerSizeType) {
        setName(headerSizeType.getTemplateName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.richnotification.SrnTemplate
    public Object cloneSelf() {
        return new SrnStandardTemplate(this);
    }
}
