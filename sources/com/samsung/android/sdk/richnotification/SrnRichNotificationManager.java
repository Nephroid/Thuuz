package com.samsung.android.sdk.richnotification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.util.TimeUtils;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* loaded from: classes.dex */
public class SrnRichNotificationManager {
    private static final int ENABLE_NOTIFICATION = 1;
    private static final String EXTRA_ENABLE_PANEL_NOTI = "[NS]extra_enable_panel_noti";
    private static final String TAG = "RichNotification";
    private static Boolean sHasPermission;
    private final Context mContext;
    private ForwardHandler mForwardHandler;
    private final LocalHandler mHandler;
    private boolean mIsStarted;
    private final List<EventListener> mListeners = new ArrayList();
    private RichNotificationReceiver mReceiver;

    /* loaded from: classes.dex */
    public interface EventListener {
        void onError(UUID uuid, ErrorType errorType);

        void onRead(UUID uuid);

        void onRemoved(UUID uuid);
    }

    /* loaded from: classes.dex */
    class LocalHandler extends Handler {
        private static final String CALLBACK_EXTRA_ERROR = "error";
        private static final String CALLBACK_EXTRA_UUID = "uuid";
        private static final String CALLBACK_EXTRA_UUID_LIST = "uuid_list";
        private static final int CALLBACK_TYPE_ALL_REMOVED = 2;
        private static final int CALLBACK_TYPE_ERROR = 3;
        private static final int CALLBACK_TYPE_READ = 0;
        private static final int CALLBACK_TYPE_REMOVED = 1;
        private final WeakReference<SrnRichNotificationManager> mManagerRef;

        /* synthetic */ LocalHandler(Context context, SrnRichNotificationManager srnRichNotificationManager, LocalHandler localHandler) {
            this(context, srnRichNotificationManager);
        }

        private LocalHandler(Context context, SrnRichNotificationManager srnRichNotificationManager) {
            super(context.getMainLooper());
            this.mManagerRef = new WeakReference<>(srnRichNotificationManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SrnRichNotificationManager srnRichNotificationManager = this.mManagerRef.get();
            if (srnRichNotificationManager == null) {
                return;
            }
            if (srnRichNotificationManager.mListeners == null || srnRichNotificationManager.mListeners.size() == 0) {
                Log.w(SrnRichNotificationManager.TAG, "No event listener registered.");
                return;
            }
            Bundle bundle = (Bundle) message.obj;
            int i = bundle.getInt(Constants.EXTRA_ACTION_TYPE, -1);
            if (i == 2) {
                handleAllRemoved(srnRichNotificationManager, bundle);
            } else {
                handleCallbacks(srnRichNotificationManager, bundle, i);
            }
        }

        private void handleAllRemoved(SrnRichNotificationManager srnRichNotificationManager, Bundle bundle) {
            ArrayList<String> stringArrayList = bundle.getStringArrayList(CALLBACK_EXTRA_UUID_LIST);
            if (stringArrayList == null) {
                Log.e(SrnRichNotificationManager.TAG, "RICH_NOTIFICATION_CALLBACK_ALL_REMOVED : uuid list is null.");
                return;
            }
            Iterator<String> it = stringArrayList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                try {
                    notifyRemoved(srnRichNotificationManager, UUID.fromString(next));
                } catch (IllegalArgumentException e) {
                    Log.e(SrnRichNotificationManager.TAG, String.format("RICH_NOTIFICATION_CALLBACK_ALL_REMOVED : uuid is not formatted correctly. (%s)", next));
                }
            }
        }

        private void handleCallbacks(SrnRichNotificationManager srnRichNotificationManager, Bundle bundle, int i) {
            String string = bundle.getString(CALLBACK_EXTRA_UUID);
            if (string == null) {
                Log.e(SrnRichNotificationManager.TAG, "RICH_NOTIFICATION_CALLBACK : uuid is null.");
                return;
            }
            try {
                UUID fromString = UUID.fromString(string);
                switch (i) {
                    case 0:
                        Log.d(SrnRichNotificationManager.TAG, "RICH_NOTIFICATION_CALLBACK_READ : " + string);
                        Iterator it = srnRichNotificationManager.mListeners.iterator();
                        while (it.hasNext()) {
                            ((EventListener) it.next()).onRead(fromString);
                        }
                        return;
                    case 1:
                        notifyRemoved(srnRichNotificationManager, fromString);
                        return;
                    case 2:
                    default:
                        return;
                    case 3:
                        int i2 = bundle.getInt("error", 0);
                        Log.d(SrnRichNotificationManager.TAG, "RICH_NOTIFICATION_CALLBACK_REMOVED : " + string + ", " + i2);
                        ErrorType errorType = ErrorType.get(i2);
                        Iterator it2 = srnRichNotificationManager.mListeners.iterator();
                        while (it2.hasNext()) {
                            ((EventListener) it2.next()).onError(fromString, errorType);
                        }
                        return;
                }
            } catch (IllegalArgumentException e) {
                Log.e(SrnRichNotificationManager.TAG, String.format("RICH_NOTIFICATION_CALLBACK : uuid is not formatted correctly. (%s)", string));
            }
        }

        private void notifyRemoved(SrnRichNotificationManager srnRichNotificationManager, UUID uuid) {
            Log.d(SrnRichNotificationManager.TAG, "RICH_NOTIFICATION_CALLBACK_REMOVED : " + uuid);
            Iterator it = srnRichNotificationManager.mListeners.iterator();
            while (it.hasNext()) {
                ((EventListener) it.next()).onRemoved(uuid);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ForwardHandler extends Handler {
        private static final int FORWARD_DISMISS = 1;
        private static final int FORWARD_DISMISS_ALL = 2;
        private static final int FORWARD_NOTIFY = 0;
        private static final int FORWARD_SHOW_ERROR_TOAST = 3;
        private final Context mContext;

        /* synthetic */ ForwardHandler(Context context, Looper looper, ForwardHandler forwardHandler) {
            this(context, looper);
        }

        private ForwardHandler(Context context, Looper looper) {
            super(looper);
            this.mContext = context;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    handleNotify((SrnRichNotification) message.obj);
                    return;
                case 1:
                    handleDismiss((UUID) message.obj);
                    return;
                case 2:
                    handleDismissAll();
                    return;
                case 3:
                    handleShowErrorToast((ErrorToast) message.obj);
                    return;
                default:
                    return;
            }
        }

        private void handleDismissAll() {
            Intent intent = new Intent(Constants.ACTION_RICH_NOTIFICATION_FORWARD);
            intent.putExtra(Constants.ACTION_RICHNOTIFICATION_FORWARD_TYPE, Constants.RICH_NOTIFICATION_DISMISS_ALL);
            SrnRichNotificationManager.sendToNotificationService(this.mContext, intent);
        }

        private void handleDismiss(UUID uuid) {
            Intent intent = new Intent(Constants.ACTION_RICH_NOTIFICATION_FORWARD);
            intent.putExtra(Constants.ACTION_RICHNOTIFICATION_FORWARD_TYPE, Constants.RICH_NOTIFICATION_DISMISS);
            intent.putExtra(Constants.EXTRA_UUID, uuid.toString());
            SrnRichNotificationManager.sendToNotificationService(this.mContext, intent);
        }

        private void handleNotify(SrnRichNotification srnRichNotification) {
            Intent intent = new Intent(Constants.ACTION_RICH_NOTIFICATION_FORWARD);
            String b = GsonHelper.getGson().b(srnRichNotification);
            intent.putExtra(Constants.ACTION_RICHNOTIFICATION_FORWARD_TYPE, Constants.RICH_NOTIFICATION_SEND);
            intent.putExtra(Constants.EXTRA_MESSAGE, b);
            intent.putExtra(Constants.EXTRA_UUID, srnRichNotification.getUuid().toString());
            intent.putExtra(Constants.EXTRA_ASSOCIATED_NOTI_ID, srnRichNotification.getAssociatedAndroidNotification());
            intent.putExtra(Constants.EXTRA_ASSOCIATED_NOTI_TAG, srnRichNotification.getAssociatedAndroidNotificationTag());
            SrnRichNotificationManager.sendToNotificationService(this.mContext, intent);
        }

        private void handleShowErrorToast(ErrorToast errorToast) {
            Intent intent = new Intent(Constants.ACTION_RICH_NOTIFICATION_FORWARD);
            intent.putExtra(Constants.ACTION_RICHNOTIFICATION_FORWARD_TYPE, Constants.RICH_NOTIFICATION_SHOW_ERROR_TOAST);
            intent.putExtra(Constants.EXTRA_UUID, errorToast.getUuid().toString());
            intent.putExtra(Constants.EXTRA_MESSAGE, GsonHelper.getGson().b(errorToast));
            SrnRichNotificationManager.sendToNotificationService(this.mContext, intent);
        }
    }

    /* loaded from: classes.dex */
    public enum ErrorType {
        UNDEFINED;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static ErrorType[] valuesCustom() {
            ErrorType[] valuesCustom = values();
            int length = valuesCustom.length;
            ErrorType[] errorTypeArr = new ErrorType[length];
            System.arraycopy(valuesCustom, 0, errorTypeArr, 0, length);
            return errorTypeArr;
        }

        static ErrorType get(int i) {
            return (i < 0 || i >= valuesCustom().length) ? UNDEFINED : valuesCustom()[i];
        }
    }

    public SrnRichNotificationManager(Context context) {
        this.mContext = context.getApplicationContext();
        this.mHandler = new LocalHandler(context, this, null);
    }

    public void start() {
        if (this.mIsStarted) {
            throw new IllegalStateException("The manager is already started.");
        }
        this.mIsStarted = true;
        HandlerThread handlerThread = new HandlerThread("ForwardThread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.mForwardHandler = new ForwardHandler(this.mContext, looper, null);
    }

    public void stop() {
        if (!this.mIsStarted) {
            throw new IllegalStateException("The manager is not started.");
        }
        this.mIsStarted = false;
        this.mForwardHandler.getLooper().quitSafely();
        this.mForwardHandler = null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0058, code lost:
    
        com.samsung.android.sdk.richnotification.SrnRichNotificationManager.sHasPermission = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.UUID notify(com.samsung.android.sdk.richnotification.SrnRichNotification r6) {
        /*
            r5 = this;
            r0 = 0
            boolean r1 = r5.mIsStarted
            if (r1 != 0) goto Ld
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "The manager is not started."
            r0.<init>(r1)
            throw r0
        Ld:
            if (r6 != 0) goto L17
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "notification is null"
            r0.<init>(r1)
            throw r0
        L17:
            java.lang.Boolean r1 = com.samsung.android.sdk.richnotification.SrnRichNotificationManager.sHasPermission
            if (r1 != 0) goto L3e
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
            com.samsung.android.sdk.richnotification.SrnRichNotificationManager.sHasPermission = r1
            android.content.Context r1 = r5.mContext     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L60
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L60
            android.content.Context r2 = r5.mContext     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L60
            java.lang.String r2 = r2.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L60
            r3 = 4096(0x1000, float:5.74E-42)
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r2, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L60
            if (r1 == 0) goto L3e
            java.lang.String[] r2 = r1.requestedPermissions     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L60
            if (r2 == 0) goto L3e
            java.lang.String[] r1 = r1.requestedPermissions     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L60
            int r2 = r1.length     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L60
        L3c:
            if (r0 < r2) goto L4e
        L3e:
            java.lang.Boolean r0 = com.samsung.android.sdk.richnotification.SrnRichNotificationManager.sHasPermission
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L65
            java.lang.SecurityException r0 = new java.lang.SecurityException
            java.lang.String r1 = "com.samsung.wmanager.ENABLE_NOTIFICATION permission is required"
            r0.<init>(r1)
            throw r0
        L4e:
            r3 = r1[r0]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L60
            java.lang.String r4 = "com.samsung.wmanager.ENABLE_NOTIFICATION"
            boolean r3 = r4.equals(r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L60
            if (r3 == 0) goto L62
            r0 = 1
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L60
            com.samsung.android.sdk.richnotification.SrnRichNotificationManager.sHasPermission = r0     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L60
            goto L3e
        L60:
            r0 = move-exception
            goto L3e
        L62:
            int r0 = r0 + 1
            goto L3c
        L65:
            r6.validateSelf()     // Catch: com.samsung.android.sdk.richnotification.SrnValidationException -> L70
            r5.sendRichNotification(r6)     // Catch: com.samsung.android.sdk.richnotification.SrnValidationException -> L70
            java.util.UUID r0 = r6.getUuid()     // Catch: com.samsung.android.sdk.richnotification.SrnValidationException -> L70
            return r0
        L70:
            r0 = move-exception
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.getMessage()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.richnotification.SrnRichNotificationManager.notify(com.samsung.android.sdk.richnotification.SrnRichNotification):java.util.UUID");
    }

    private void sendRichNotification(SrnRichNotification srnRichNotification) {
        Log.i(TAG, "sendRichNotification(RichNotification notification)");
        this.mForwardHandler.obtainMessage(0, new SrnRichNotification(srnRichNotification)).sendToTarget();
    }

    public void showErrorToast(UUID uuid, String str) {
        if (!this.mIsStarted) {
            throw new IllegalStateException("The manager is not started.");
        }
        if (uuid == null) {
            throw new NullPointerException("uuid is null.");
        }
        if (str == null) {
            throw new NullPointerException("errorMessage is null.");
        }
        this.mForwardHandler.obtainMessage(3, new ErrorToast(uuid, str)).sendToTarget();
    }

    public void dismiss(UUID uuid) {
        if (!this.mIsStarted) {
            throw new IllegalStateException("The manager is not started.");
        }
        if (uuid == null) {
            throw new NullPointerException("uuid is null.");
        }
        Log.i(TAG, "dismiss");
        this.mForwardHandler.obtainMessage(1, uuid).sendToTarget();
    }

    public void dismissAll() {
        if (!this.mIsStarted) {
            throw new IllegalStateException("The manager is not started.");
        }
        Log.i(TAG, "dismissAll");
        this.mForwardHandler.obtainMessage(2).sendToTarget();
    }

    public void registerRichNotificationListener(EventListener eventListener) {
        if (eventListener == null) {
            throw new NullPointerException("listener is null.");
        }
        if (this.mListeners.size() == 0) {
            this.mReceiver = new RichNotificationReceiver(this, null);
            this.mContext.registerReceiver(this.mReceiver, new IntentFilter(Constants.ACTION_RICH_NOTIFICATION_BACKWARD));
        }
        this.mListeners.add(eventListener);
    }

    public void unregisterRichNotificationListener(EventListener eventListener) {
        if (this.mListeners.size() != 0) {
            this.mListeners.remove(eventListener);
            if (this.mListeners.size() == 0) {
                this.mContext.unregisterReceiver(this.mReceiver);
                this.mReceiver = null;
            }
        }
    }

    public boolean isConnected() {
        return Settings.System.getInt(this.mContext.getContentResolver(), "RICH_NOTIFICATION_AVAILABLE", 0) == 1;
    }

    @TargetApi(TimeUtils.HUNDRED_DAY_FIELD_LEN)
    public static void setRouteCondition(Notification notification) {
        if (notification != null) {
            notification.extras.putInt(EXTRA_ENABLE_PANEL_NOTI, 1);
        }
    }

    /* loaded from: classes.dex */
    class RichNotificationReceiver extends BroadcastReceiver {
        private RichNotificationReceiver() {
        }

        /* synthetic */ RichNotificationReceiver(SrnRichNotificationManager srnRichNotificationManager, RichNotificationReceiver richNotificationReceiver) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.ACTION_RICH_NOTIFICATION_BACKWARD)) {
                if (context.getPackageName().equals(intent.getStringExtra(Constants.EXTRA_PACKAGENAME))) {
                    Message obtainMessage = SrnRichNotificationManager.this.mHandler.obtainMessage();
                    obtainMessage.obj = intent.getExtras();
                    SrnRichNotificationManager.this.mHandler.sendMessage(obtainMessage);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendToNotificationService(Context context, Intent intent) {
        intent.putExtra(Constants.EXTRA_PACKAGENAME, context.getPackageName());
        intent.setPackage(Constants.GOPROVIDER_PACKAGE);
        context.sendBroadcast(intent);
    }
}
