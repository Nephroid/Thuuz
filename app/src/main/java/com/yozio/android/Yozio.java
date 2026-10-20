package com.yozio.android;

import android.content.Context;
import com.yozio.android.helpers.Email;
import com.yozio.android.interfaces.GetMetaDataCallback;

/* loaded from: classes.dex */
public class Yozio {
    public static boolean YOZIO_ENABLE_LOGGING = true;
    public static int YOZIO_CONNECTION_TIMEOUT = Constants.YOZIO_SMS_SEND_LIMIT;
    public static int YOZIO_SOCKET_TIMEOUT = Constants.YOZIO_EVENT_QUEUE_MAX_SIZE;

    public static void setAppKeyAndSecretKey(Context context, String str, String str2) {
        YozioService.getInstance().setAppKeyAndSecretKey(context, str, str2, null);
    }

    public static void setAppKeyAndSecretKey(Context context, String str, String str2, GetMetaDataCallback getMetaDataCallback) {
        YozioService.getInstance().setAppKeyAndSecretKey(context, str, str2, getMetaDataCallback);
    }

    public static Class<?> getInviteActivityClass() {
        return YozioService.getInstance().getInviteActivityClass();
    }

    public static void setUserEmailAndName(String str, String str2) {
        Email.getInstance().setSenderNameAndEmail(str2, str);
    }
}
