package com.facebook;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.Session;
import com.facebook.internal.LikeActionController;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.PendingCallStore;
import com.facebook.widget.FacebookDialog;
import java.util.UUID;

/* loaded from: classes.dex */
public class UiLifecycleHelper {
    private static final String ACTIVITY_NULL_MESSAGE = "activity cannot be null";
    private static final String DIALOG_CALL_ID_SAVE_KEY = "com.facebook.UiLifecycleHelper.pendingFacebookDialogCallKey";
    private final Activity activity;
    private AppEventsLogger appEventsLogger;
    private final LocalBroadcastManager broadcastManager;
    private final Session.StatusCallback callback;
    private UUID pendingFacebookDialogCallId;
    private PendingCallStore pendingFacebookDialogCallStore;
    private final BroadcastReceiver receiver;

    public UiLifecycleHelper(Activity activity, Session.StatusCallback statusCallback) {
        if (activity == null) {
            throw new IllegalArgumentException(ACTIVITY_NULL_MESSAGE);
        }
        this.activity = activity;
        this.callback = statusCallback;
        this.receiver = new ActiveSessionBroadcastReceiver();
        this.broadcastManager = LocalBroadcastManager.getInstance(activity);
        this.pendingFacebookDialogCallStore = PendingCallStore.getInstance();
        Settings.sdkInitialize(activity);
    }

    public void onCreate(Bundle bundle) {
        Session activeSession = Session.getActiveSession();
        if (activeSession == null) {
            if (bundle != null) {
                activeSession = Session.restoreSession(this.activity, null, this.callback, bundle);
            }
            if (activeSession == null) {
                activeSession = new Session(this.activity);
            }
            Session.setActiveSession(activeSession);
        }
        if (bundle != null) {
            String string = bundle.getString(DIALOG_CALL_ID_SAVE_KEY);
            if (string != null) {
                this.pendingFacebookDialogCallId = UUID.fromString(string);
            }
            this.pendingFacebookDialogCallStore.restoreFromSavedInstanceState(bundle);
        }
    }

    public void onResume() {
        Session activeSession = Session.getActiveSession();
        if (activeSession != null) {
            if (this.callback != null) {
                activeSession.addCallback(this.callback);
            }
            if (SessionState.CREATED_TOKEN_LOADED.equals(activeSession.getState())) {
                activeSession.openForRead(null);
            }
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Session.ACTION_ACTIVE_SESSION_SET);
        intentFilter.addAction(Session.ACTION_ACTIVE_SESSION_UNSET);
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        onActivityResult(i, i2, intent, null);
    }

    public void onActivityResult(int i, int i2, Intent intent, FacebookDialog.Callback callback) {
        Session activeSession = Session.getActiveSession();
        if (activeSession != null) {
            activeSession.onActivityResult(this.activity, i, i2, intent);
        }
        if (!LikeActionController.handleOnActivityResult(this.activity, i, i2, intent)) {
            handleFacebookDialogActivityResult(i, i2, intent, callback);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        Session.saveSession(Session.getActiveSession(), bundle);
        if (this.pendingFacebookDialogCallId != null) {
            bundle.putString(DIALOG_CALL_ID_SAVE_KEY, this.pendingFacebookDialogCallId.toString());
        }
        this.pendingFacebookDialogCallStore.saveInstanceState(bundle);
    }

    public void onPause() {
        Session activeSession;
        this.broadcastManager.unregisterReceiver(this.receiver);
        if (this.callback != null && (activeSession = Session.getActiveSession()) != null) {
            activeSession.removeCallback(this.callback);
        }
    }

    public void onStop() {
        AppEventsLogger.onContextStop();
    }

    public void onDestroy() {
    }

    public void trackPendingDialogCall(FacebookDialog.PendingCall pendingCall) {
        if (this.pendingFacebookDialogCallId != null) {
            Log.i("Facebook", "Tracking new app call while one is still pending; canceling pending call.");
            cancelPendingAppCall(null);
        }
        if (pendingCall != null) {
            this.pendingFacebookDialogCallId = pendingCall.getCallId();
            this.pendingFacebookDialogCallStore.trackPendingCall(pendingCall);
        }
    }

    public AppEventsLogger getAppEventsLogger() {
        Session activeSession = Session.getActiveSession();
        if (activeSession == null) {
            return null;
        }
        if (this.appEventsLogger == null || !this.appEventsLogger.isValidForSession(activeSession)) {
            if (this.appEventsLogger != null) {
                AppEventsLogger.onContextStop();
            }
            this.appEventsLogger = AppEventsLogger.newLogger(this.activity, activeSession);
        }
        return this.appEventsLogger;
    }

    /* loaded from: classes.dex */
    class ActiveSessionBroadcastReceiver extends BroadcastReceiver {
        private ActiveSessionBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Session activeSession;
            if (Session.ACTION_ACTIVE_SESSION_SET.equals(intent.getAction())) {
                Session activeSession2 = Session.getActiveSession();
                if (activeSession2 != null && UiLifecycleHelper.this.callback != null) {
                    activeSession2.addCallback(UiLifecycleHelper.this.callback);
                    return;
                }
                return;
            }
            if (Session.ACTION_ACTIVE_SESSION_UNSET.equals(intent.getAction()) && (activeSession = Session.getActiveSession()) != null && UiLifecycleHelper.this.callback != null) {
                activeSession.removeCallback(UiLifecycleHelper.this.callback);
            }
        }
    }

    private boolean handleFacebookDialogActivityResult(int i, int i2, Intent intent, FacebookDialog.Callback callback) {
        FacebookDialog.PendingCall pendingCallById;
        if (this.pendingFacebookDialogCallId == null || (pendingCallById = this.pendingFacebookDialogCallStore.getPendingCallById(this.pendingFacebookDialogCallId)) == null || pendingCallById.getRequestCode() != i) {
            return false;
        }
        if (intent == null) {
            cancelPendingAppCall(callback);
            return true;
        }
        UUID callIdFromIntent = NativeProtocol.getCallIdFromIntent(intent);
        if (callIdFromIntent != null && this.pendingFacebookDialogCallId.equals(callIdFromIntent)) {
            FacebookDialog.handleActivityResult(this.activity, pendingCallById, i, intent, callback);
        } else {
            cancelPendingAppCall(callback);
        }
        stopTrackingPendingAppCall();
        return true;
    }

    private void cancelPendingAppCall(FacebookDialog.Callback callback) {
        FacebookDialog.PendingCall pendingCallById;
        if (this.pendingFacebookDialogCallId != null && (pendingCallById = this.pendingFacebookDialogCallStore.getPendingCallById(this.pendingFacebookDialogCallId)) != null) {
            if (callback != null) {
                Intent requestIntent = pendingCallById.getRequestIntent();
                Intent intent = new Intent();
                intent.putExtra(NativeProtocol.EXTRA_PROTOCOL_CALL_ID, requestIntent.getStringExtra(NativeProtocol.EXTRA_PROTOCOL_CALL_ID));
                intent.putExtra(NativeProtocol.EXTRA_PROTOCOL_ACTION, requestIntent.getStringExtra(NativeProtocol.EXTRA_PROTOCOL_ACTION));
                intent.putExtra(NativeProtocol.EXTRA_PROTOCOL_VERSION, requestIntent.getIntExtra(NativeProtocol.EXTRA_PROTOCOL_VERSION, 0));
                intent.putExtra(NativeProtocol.STATUS_ERROR_TYPE, NativeProtocol.ERROR_UNKNOWN_ERROR);
                FacebookDialog.handleActivityResult(this.activity, pendingCallById, pendingCallById.getRequestCode(), intent, callback);
            }
            stopTrackingPendingAppCall();
        }
    }

    private void stopTrackingPendingAppCall() {
        this.pendingFacebookDialogCallStore.stopTrackingPendingCall(this.pendingFacebookDialogCallId);
        this.pendingFacebookDialogCallId = null;
    }
}
