package com.samsung.android.sdk.richnotification;

import defpackage.axf;
import defpackage.axg;

/* loaded from: classes.dex */
public abstract class SrnTemplate {
    protected static final String TEMPLATE_TYPE_ADDITIONAL = "additional_template";
    protected static final String TEMPLATE_TYPE_DEFAULT = "default";

    @axf
    @axg(a = "template_name")
    private String mName;

    @axf
    @axg(a = "template_priority")
    private final String mPriority;

    @axf
    @axg(a = "template_type")
    private final String mType;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object cloneSelf();

    /* loaded from: classes.dex */
    public enum Priority {
        PRIMARY,
        SECONDARY;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static Priority[] valuesCustom() {
            Priority[] valuesCustom = values();
            int length = valuesCustom.length;
            Priority[] priorityArr = new Priority[length];
            System.arraycopy(valuesCustom, 0, priorityArr, 0, length);
            return priorityArr;
        }

        @Override // java.lang.Enum
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SrnTemplate(Priority priority, String str, String str2) {
        this.mName = str;
        this.mPriority = priority.toString();
        this.mType = str2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SrnTemplate(SrnTemplate srnTemplate) {
        this.mName = srnTemplate.mName;
        this.mPriority = srnTemplate.mPriority;
        this.mType = srnTemplate.mType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setName(String str) {
        this.mName = str;
    }
}
