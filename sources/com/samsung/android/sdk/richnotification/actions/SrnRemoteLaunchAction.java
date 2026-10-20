package com.samsung.android.sdk.richnotification.actions;

import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.sdk.richnotification.SrnAction;
import com.samsung.android.sdk.richnotification.SrnImageAsset;
import defpackage.axf;
import defpackage.axg;

/* loaded from: classes.dex */
public class SrnRemoteLaunchAction extends SrnAction {

    @axf
    @axg(a = "extras")
    private Bundle mExtras;

    @axf
    @axg(a = "icon")
    private SrnImageAsset mIcon;

    @axf
    @axg(a = "mime_type")
    private String mMimeType;

    @axf
    @axg(a = "operation")
    private String mOperation;

    @axf
    @axg(a = "package")
    private String mPackageName;

    @axf
    @axg(a = "uri")
    private Uri mUri;

    public SrnRemoteLaunchAction(String str) {
        this(str, null);
    }

    public SrnRemoteLaunchAction(String str, Uri uri) {
        super(SrnAction.ActionType.REMOTE_LAUNCH, str);
        this.mOperation = "OP_LAUNCH";
        this.mUri = uri;
    }

    private SrnRemoteLaunchAction(SrnRemoteLaunchAction srnRemoteLaunchAction) {
        super(srnRemoteLaunchAction);
        this.mOperation = "OP_LAUNCH";
        this.mIcon = srnRemoteLaunchAction.mIcon;
        this.mOperation = srnRemoteLaunchAction.mOperation;
        this.mPackageName = srnRemoteLaunchAction.mPackageName;
        this.mUri = srnRemoteLaunchAction.mUri;
        this.mMimeType = srnRemoteLaunchAction.mMimeType;
        if (srnRemoteLaunchAction.mExtras != null) {
            this.mExtras = new Bundle(srnRemoteLaunchAction.mExtras);
        }
    }

    public void setIcon(SrnImageAsset srnImageAsset) {
        this.mIcon = srnImageAsset;
    }

    public void setData(Uri uri) {
        this.mUri = uri;
    }

    public void setMimeType(String str) {
        this.mMimeType = str;
    }

    public void setPackage(String str) {
        this.mPackageName = str;
    }

    public void setExtras(Bundle bundle) {
        this.mExtras = bundle;
    }

    public void setOperation(String str) {
        this.mOperation = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.richnotification.SrnAction
    public SrnAction cloneSelf() {
        return new SrnRemoteLaunchAction(this);
    }
}
