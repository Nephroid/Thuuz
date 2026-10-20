package com.samsung.android.sdk.richnotification.templates;

import com.samsung.android.sdk.richnotification.SrnImageAsset;
import defpackage.axf;
import defpackage.axg;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
final class SrnEventSecondaryTemplate extends SrnSecondaryTemplate {
    private static final String TEMPLATE_NAME = "event_template";

    @axf
    @axg(a = "list_body")
    private List<BodyItem> mListBody;

    @axf
    @axg(a = "list_title")
    private List<TitleItem> mListTitle;

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

    /* loaded from: classes.dex */
    class TitleItem {

        @axf
        @axg(a = "title")
        private final String mValue;

        private TitleItem(String str) {
            this.mValue = str;
        }

        /* synthetic */ TitleItem(String str, TitleItem titleItem) {
            this(str);
        }
    }

    /* loaded from: classes.dex */
    class BodyItem {

        @axf
        @axg(a = "body")
        private final String mValue;

        private BodyItem(String str) {
            this.mValue = str;
        }

        /* synthetic */ BodyItem(String str, BodyItem bodyItem) {
            this(str);
        }
    }

    public SrnEventSecondaryTemplate() {
        super(TEMPLATE_NAME, "additional_template");
    }

    private SrnEventSecondaryTemplate(SrnEventSecondaryTemplate srnEventSecondaryTemplate) {
        super(srnEventSecondaryTemplate);
        this.mTitle = srnEventSecondaryTemplate.mTitle;
        this.mSubHeader = srnEventSecondaryTemplate.mSubHeader;
        if (srnEventSecondaryTemplate.mListTitle != null) {
            this.mListTitle = new ArrayList(srnEventSecondaryTemplate.mListTitle);
        }
        if (srnEventSecondaryTemplate.mListBody != null) {
            this.mListBody = new ArrayList(srnEventSecondaryTemplate.mListBody);
        }
        this.mSmallIcon = srnEventSecondaryTemplate.mSmallIcon;
        this.mSmallIconTitle = srnEventSecondaryTemplate.mSmallIconTitle;
        this.mSmallIcon2 = srnEventSecondaryTemplate.mSmallIcon2;
        this.mSmallIconTitle2 = srnEventSecondaryTemplate.mSmallIconTitle2;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setSubHeader(String str) {
        this.mSubHeader = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addListItem(String str, String str2) {
        TitleItem titleItem = null;
        Object[] objArr = 0;
        if (this.mListTitle == null || this.mListBody == null) {
            this.mListTitle = new ArrayList();
            this.mListBody = new ArrayList();
        }
        this.mListTitle.add(new TitleItem(str, titleItem));
        this.mListBody.add(new BodyItem(str2, objArr == true ? 1 : 0));
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
        return new SrnEventSecondaryTemplate(this);
    }
}
