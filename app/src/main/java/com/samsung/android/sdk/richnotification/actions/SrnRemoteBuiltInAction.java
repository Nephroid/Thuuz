package com.samsung.android.sdk.richnotification.actions;

import android.net.Uri;
import com.samsung.android.sdk.richnotification.SrnAction;
import defpackage.axf;
import defpackage.axg;

/* loaded from: classes.dex */
public class SrnRemoteBuiltInAction extends SrnAction {

    @axf
    @axg(a = "system_type")
    private OperationType mOperation;

    @axf
    @axg(a = "uri")
    private Uri mUri;

    /* loaded from: classes.dex */
    public enum OperationType {
        CALL,
        SMS;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static OperationType[] valuesCustom() {
            OperationType[] valuesCustom = values();
            int length = valuesCustom.length;
            OperationType[] operationTypeArr = new OperationType[length];
            System.arraycopy(valuesCustom, 0, operationTypeArr, 0, length);
            return operationTypeArr;
        }
    }

    public SrnRemoteBuiltInAction(String str) {
        this(str, OperationType.CALL);
    }

    public SrnRemoteBuiltInAction(String str, OperationType operationType) {
        super(SrnAction.ActionType.REMOTE_BUILT_IN, str);
        this.mOperation = OperationType.CALL;
        if (operationType == null) {
            throw new NullPointerException("opType is null,");
        }
        this.mOperation = operationType;
    }

    private SrnRemoteBuiltInAction(SrnRemoteBuiltInAction srnRemoteBuiltInAction) {
        super(srnRemoteBuiltInAction);
        this.mOperation = OperationType.CALL;
        this.mOperation = srnRemoteBuiltInAction.mOperation;
        this.mUri = srnRemoteBuiltInAction.mUri;
    }

    public void setType(OperationType operationType) {
        if (operationType == null) {
            throw new NullPointerException("opType is null,");
        }
        this.mOperation = operationType;
    }

    public void setData(Uri uri) {
        this.mUri = uri;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.richnotification.SrnAction
    public SrnAction cloneSelf() {
        return new SrnRemoteBuiltInAction(this);
    }
}
