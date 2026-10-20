package com.thuuz.gtv;

import android.content.Context;
import defpackage.bzy;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class PlatformOperations extends bzy {
    public static final String AMZN = "com.thuuz.amzn.AmznOperations";
    public static final int EVENT_CONFIGURE_MYSPORTS_MYTEAMS = 1;
    public static final String GTV = "com.thuuz.gtv.GtvOperations";
    public static final String MOBILE = "com.thuuz.mob.MobileOperations";

    public static PlatformOperations getPlatformOperationsInstance(String str) {
        if (str.contentEquals("com.thuuz.mob")) {
            try {
                return (PlatformOperations) Class.forName(MOBILE).newInstance();
            } catch (Throwable th) {
                Logger.getLogger(PlatformOperations.class.getSimpleName()).log(Level.SEVERE, "problem: ", th);
                return null;
            }
        }
        if (str.contentEquals("com.thuuz.gtv")) {
            try {
                return (PlatformOperations) Class.forName(GTV).newInstance();
            } catch (Throwable th2) {
                Logger.getLogger(PlatformOperations.class.getSimpleName()).log(Level.SEVERE, "problem: ", th2);
                return null;
            }
        }
        if (str.contentEquals("com.thuuz.amzn")) {
            try {
                return (PlatformOperations) Class.forName(AMZN).newInstance();
            } catch (Throwable th3) {
                Logger.getLogger(PlatformOperations.class.getSimpleName()).log(Level.SEVERE, "problem: ", th3);
            }
        }
        return null;
    }

    public void initialActivityOnCreate(Context context, boolean z) {
    }

    public void appOnStart(Context context) {
    }

    public void appOnStop(Context context) {
    }

    public void sendEvent(Context context, int i) {
    }
}
