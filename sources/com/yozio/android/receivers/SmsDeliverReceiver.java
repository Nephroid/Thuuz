package com.yozio.android.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.yozio.android.Constants;
import com.yozio.android.YozioService;

/* loaded from: classes.dex */
public class SmsDeliverReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int resultCode = getResultCode();
        switch (resultCode) {
            case -1:
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_SMS_DELIVER_RESULT_OK, Integer.toString(resultCode), null, false);
                return;
            case 0:
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_SMS_DELIVER_RESULT_CANCELED, Integer.toString(resultCode), null, false);
                return;
            default:
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_SMS_DELIVER_RESULT_UNKNOWN_ERROR, Integer.toString(resultCode), null, false);
                return;
        }
    }
}
