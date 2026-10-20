package com.yozio.android.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.yozio.android.Constants;
import com.yozio.android.YozioService;

/* loaded from: classes.dex */
public class SmsSentReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int resultCode = getResultCode();
        switch (getResultCode()) {
            case -1:
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_SMS_SENT_RESULT_OK, Integer.toString(resultCode), null, false);
                return;
            case 0:
            default:
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_SMS_SENT_RESULT_UNKNOWN_ERROR, Integer.toString(resultCode), null, false);
                return;
            case 1:
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_SMS_SENT_RESULT_ERROR_GENERIC_FAILURE, Integer.toString(resultCode), null, false);
                return;
            case 2:
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_SMS_SENT_RESULT_ERROR_RADIO_OFF, Integer.toString(resultCode), null, false);
                return;
            case 3:
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_SMS_SENT_RESULT_ERROR_NULL_PDU, Integer.toString(resultCode), null, false);
                return;
            case 4:
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_SMS_SENT_RESULT_ERROR_NO_SERVICE, Integer.toString(resultCode), null, false);
                return;
        }
    }
}
