package com.google.android.gcm;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import defpackage.cr;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public abstract class GCMBaseIntentService extends IntentService {
    private static PowerManager.WakeLock a;
    private static final Object b = GCMBaseIntentService.class;
    private static int d = 0;
    private static final Random e = new Random();
    private static final int f = (int) TimeUnit.SECONDS.toMillis(3600);
    private static final String g = Long.toBinaryString(e.nextLong());
    private final String[] c;

    public abstract void a(Context context, Intent intent);

    public abstract void b(Context context, String str);

    public abstract void c(Context context, String str);

    public abstract void d(Context context, String str);

    protected GCMBaseIntentService() {
        this(a("DynamicSenderIds"), null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GCMBaseIntentService(String... strArr) {
        this(a(strArr), strArr);
    }

    private GCMBaseIntentService(String str, String[] strArr) {
        super(str);
        this.c = strArr;
    }

    private static String a(String str) {
        StringBuilder append = new StringBuilder().append("GCMIntentService-").append(str).append("-");
        int i = d + 1;
        d = i;
        String sb = append.append(i).toString();
        Log.v("GCMBaseIntentService", "Intent service name: " + sb);
        return sb;
    }

    private static String a(String[] strArr) {
        return a(cr.a(strArr));
    }

    protected String[] a(Context context) {
        if (this.c == null) {
            throw new IllegalStateException("sender id not set on constructor");
        }
        return this.c;
    }

    protected void a(Context context, int i) {
    }

    protected boolean a(Context context, String str) {
        return true;
    }

    @Override // android.app.IntentService
    public final void onHandleIntent(Intent intent) {
        try {
            Context applicationContext = getApplicationContext();
            String action = intent.getAction();
            if (action.equals("com.google.android.c2dm.intent.REGISTRATION")) {
                cr.e(applicationContext);
                b(applicationContext, intent);
            } else if (action.equals("com.google.android.c2dm.intent.RECEIVE")) {
                String stringExtra = intent.getStringExtra("message_type");
                if (stringExtra == null) {
                    a(applicationContext, intent);
                } else if (stringExtra.equals("deleted_messages")) {
                    String stringExtra2 = intent.getStringExtra("total_deleted");
                    if (stringExtra2 != null) {
                        try {
                            int parseInt = Integer.parseInt(stringExtra2);
                            Log.v("GCMBaseIntentService", "Received deleted messages notification: " + parseInt);
                            a(applicationContext, parseInt);
                        } catch (NumberFormatException e2) {
                            Log.e("GCMBaseIntentService", "GCM returned invalid number of deleted messages: " + stringExtra2);
                        }
                    }
                } else {
                    Log.e("GCMBaseIntentService", "Received unknown special message: " + stringExtra);
                }
            } else if (action.equals("com.google.android.gcm.intent.RETRY")) {
                String stringExtra3 = intent.getStringExtra(ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN);
                if (!g.equals(stringExtra3)) {
                    Log.e("GCMBaseIntentService", "Received invalid token: " + stringExtra3);
                    synchronized (b) {
                        if (a != null) {
                            Log.v("GCMBaseIntentService", "Releasing wakelock");
                            a.release();
                        } else {
                            Log.e("GCMBaseIntentService", "Wakelock reference is null");
                        }
                    }
                    return;
                }
                if (cr.g(applicationContext)) {
                    cr.d(applicationContext);
                } else {
                    cr.b(applicationContext, a(applicationContext));
                }
            }
            synchronized (b) {
                if (a != null) {
                    Log.v("GCMBaseIntentService", "Releasing wakelock");
                    a.release();
                } else {
                    Log.e("GCMBaseIntentService", "Wakelock reference is null");
                }
            }
        } catch (Throwable th) {
            synchronized (b) {
                if (a != null) {
                    Log.v("GCMBaseIntentService", "Releasing wakelock");
                    a.release();
                } else {
                    Log.e("GCMBaseIntentService", "Wakelock reference is null");
                }
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Context context, Intent intent, String str) {
        synchronized (b) {
            if (a == null) {
                a = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "GCM_LIB");
            }
        }
        Log.v("GCMBaseIntentService", "Acquiring wakelock");
        a.acquire();
        intent.setClassName(context, str);
        context.startService(intent);
    }

    private void b(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("registration_id");
        String stringExtra2 = intent.getStringExtra("error");
        String stringExtra3 = intent.getStringExtra("unregistered");
        Log.d("GCMBaseIntentService", "handleRegistration: registrationId = " + stringExtra + ", error = " + stringExtra2 + ", unregistered = " + stringExtra3);
        if (stringExtra != null) {
            cr.i(context);
            cr.a(context, stringExtra);
            c(context, stringExtra);
            return;
        }
        if (stringExtra3 != null) {
            cr.i(context);
            d(context, cr.h(context));
            return;
        }
        Log.d("GCMBaseIntentService", "Registration error: " + stringExtra2);
        if ("SERVICE_NOT_AVAILABLE".equals(stringExtra2)) {
            if (a(context, stringExtra2)) {
                int j = cr.j(context);
                int nextInt = e.nextInt(j) + (j / 2);
                Log.d("GCMBaseIntentService", "Scheduling registration retry, backoff = " + nextInt + " (" + j + ")");
                Intent intent2 = new Intent("com.google.android.gcm.intent.RETRY");
                intent2.putExtra(ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN, g);
                ((AlarmManager) context.getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + nextInt, PendingIntent.getBroadcast(context, 0, intent2, 0));
                if (j < f) {
                    cr.a(context, j * 2);
                    return;
                }
                return;
            }
            Log.d("GCMBaseIntentService", "Not retrying failed operation");
            return;
        }
        b(context, stringExtra2);
    }
}
