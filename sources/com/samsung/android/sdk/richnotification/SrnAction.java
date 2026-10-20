package com.samsung.android.sdk.richnotification;

import android.content.Intent;
import android.os.Parcel;
import android.util.Base64;
import defpackage.awu;
import defpackage.awx;
import defpackage.awy;
import defpackage.awz;
import defpackage.axf;
import defpackage.axg;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public abstract class SrnAction {
    private static int incrementalID = 0;

    @axf
    @axg(a = "intent")
    private CallbackIntent mCallbackIntent;

    @axf
    @axg(a = "id")
    private final String mId;

    @axf
    @axg(a = "label")
    private final String mLabel;

    @axf
    @axg(a = "type")
    private final ActionType mType;

    /* loaded from: classes.dex */
    public enum ActionType {
        HOST,
        REMOTE_BUILT_IN,
        REMOTE_LAUNCH,
        REMOTE_INPUT;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static ActionType[] valuesCustom() {
            ActionType[] valuesCustom = values();
            int length = valuesCustom.length;
            ActionType[] actionTypeArr = new ActionType[length];
            System.arraycopy(valuesCustom, 0, actionTypeArr, 0, length);
            return actionTypeArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract SrnAction cloneSelf();

    /* loaded from: classes.dex */
    public final class CallbackIntent {
        private final Intent mIntent;

        /* loaded from: classes.dex */
        enum CallbackType {
            ACTIVITY_CALLBACK("a"),
            BROADCAST_CALLBACK("b"),
            SERVICE_CALLBACK("s");

            private final String mAbbreviation;

            /* renamed from: values, reason: to resolve conflict with enum method */
            public static CallbackType[] valuesCustom() {
                CallbackType[] valuesCustom = values();
                int length = valuesCustom.length;
                CallbackType[] callbackTypeArr = new CallbackType[length];
                System.arraycopy(valuesCustom, 0, callbackTypeArr, 0, length);
                return callbackTypeArr;
            }

            CallbackType(String str) {
                this.mAbbreviation = str;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public String getAbbreviation() {
                return this.mAbbreviation;
            }
        }

        private CallbackIntent(Intent intent, CallbackType callbackType) {
            if (intent == null) {
                throw new NullPointerException("intent is null.");
            }
            intent.putExtra(Constants.EXTRA_CALLBACK_INTENT_TYPE, callbackType.getAbbreviation());
            this.mIntent = intent;
        }

        public static CallbackIntent getActivityCallback(Intent intent) {
            return new CallbackIntent(intent, CallbackType.ACTIVITY_CALLBACK);
        }

        public static CallbackIntent getBroadcastCallback(Intent intent) {
            return new CallbackIntent(intent, CallbackType.BROADCAST_CALLBACK);
        }

        public static CallbackIntent getServiceCallback(Intent intent) {
            return new CallbackIntent(intent, CallbackType.SERVICE_CALLBACK);
        }
    }

    /* loaded from: classes.dex */
    final class CallbackIntentSerializer implements awz<CallbackIntent> {
        @Override // defpackage.awz
        public awu serialize(CallbackIntent callbackIntent, Type type, awy awyVar) {
            Parcel obtain = Parcel.obtain();
            callbackIntent.mIntent.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            return new awx(Base64.encodeToString(marshall, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SrnAction(ActionType actionType, String str) {
        if (str == null) {
            throw new NullPointerException("label is null.");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("label is an empty string.");
        }
        int i = incrementalID + 1;
        incrementalID = i;
        this.mId = Integer.toString(i);
        this.mType = actionType;
        this.mLabel = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SrnAction(SrnAction srnAction) {
        this.mId = srnAction.mId;
        this.mType = srnAction.mType;
        this.mLabel = srnAction.mLabel;
        this.mCallbackIntent = srnAction.mCallbackIntent;
    }

    public void setCallbackIntent(CallbackIntent callbackIntent) {
        this.mCallbackIntent = callbackIntent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getLabel() {
        return this.mLabel;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateSelf() {
    }
}
