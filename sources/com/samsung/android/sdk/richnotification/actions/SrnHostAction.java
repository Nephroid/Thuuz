package com.samsung.android.sdk.richnotification.actions;

import com.samsung.android.sdk.richnotification.SrnAction;
import com.samsung.android.sdk.richnotification.SrnImageAsset;
import defpackage.axf;
import defpackage.axg;

/* loaded from: classes.dex */
public class SrnHostAction extends SrnAction {

    @axf
    @axg(a = "icon")
    private SrnImageAsset mIcon;

    @axf
    @axg(a = "toast")
    private String mToast;

    public SrnHostAction(String str) {
        super(SrnAction.ActionType.HOST, str);
    }

    private SrnHostAction(SrnHostAction srnHostAction) {
        super(srnHostAction);
        this.mToast = srnHostAction.mToast;
        this.mIcon = srnHostAction.mIcon;
    }

    public void setToast(String str) {
        this.mToast = str;
    }

    public void setIcon(SrnImageAsset srnImageAsset) {
        this.mIcon = srnImageAsset;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.richnotification.SrnAction
    public SrnAction cloneSelf() {
        return new SrnHostAction(this);
    }
}
