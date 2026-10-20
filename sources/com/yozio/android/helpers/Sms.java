package com.yozio.android.helpers;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import com.yozio.android.Constants;
import com.yozio.android.YozioService;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Sms {
    private static Intent _sentIntent = new Intent("SMS_SENT");
    private static Intent _deliveredIntent = new Intent("SMS_DELIVERED");
    private static PendingIntent _sentPI = PendingIntent.getBroadcast(YozioService.getInstance().getContext(), 0, _sentIntent, 0);
    private static PendingIntent _deliveredPI = PendingIntent.getBroadcast(YozioService.getInstance().getContext(), 0, _deliveredIntent, 0);

    public static void sendSms(Object[] objArr, String str) {
        YozioService.log(Constants.LOG_LEVEL.INFO, "sending sms to [" + objArr.length + "] users with msg: " + str);
        SmsManager smsManager = SmsManager.getDefault();
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (i > 3000) {
                YozioService.log(Constants.LOG_LEVEL.ERROR, "Sms - sending over limit 3000");
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_SMS_OVER_LIMIT, Integer.toString(Constants.YOZIO_SMS_SEND_LIMIT), null, false);
                return;
            }
            String str2 = (String) objArr[i];
            if (!PhoneNumberUtils.isWellFormedSmsAddress(str2)) {
                YozioService.log(Constants.LOG_LEVEL.ERROR, "Sms - invalid sms # " + str2);
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_SMS_INVALID_NUMBER, str2, null, false);
            } else {
                YozioService.log(Constants.LOG_LEVEL.INFO, "sending sms to [" + str2 + "] + count: " + Integer.toString(i));
                try {
                    smsManager.sendTextMessage(str2, null, str, _sentPI, _deliveredPI);
                } catch (Exception e) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("exception", e.getMessage());
                    YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_SMS_SEND_EXCEPTION, str2, hashMap, false);
                    YozioService.log(Constants.LOG_LEVEL.ERROR, "failed to send sms to:" + str2);
                    YozioService.log(Constants.LOG_LEVEL.ERROR, e.getMessage());
                    YozioService.log(Constants.LOG_LEVEL.ERROR, e.getStackTrace().toString());
                }
            }
        }
    }
}
