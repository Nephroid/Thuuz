package com.thuuz.mob;

import android.content.Context;
// [removed legacy tracker import]
import com.thuuz.gtv.App;
import com.thuuz.gtv.PlatformOperations;
// [removed legacy tracker import]
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class MobileOperations extends PlatformOperations {
    public static final String TAPJOY_CONFIGURE_MY_SPORTS_AND_TEAMS = "1f2df2da-f0de-4ae1-93b9-7ec262d62bd4";
    private static final String TRIAL_PAY_SEC_KEY = "0067b8f0cd1fc69a080548671beecd8264bb9484fbd5786e0e7728d70a52a9c2";
    private static final String TRIAL_PAY_THUUZ_REG_ID = "1713902";
    private static boolean flurryStarted;

    @Override // com.thuuz.gtv.PlatformOperations
    public void initialActivityOnCreate(Context context, boolean z) {
        try {
            getLogger().info("initializing mobile...");
            if (z && canManipulateFiles(context)) {
                FlurryAgent.setContinueSessionMillis(5000L);
                FlurryAgent.setCaptureUncaughtExceptions(false);
                FlurryAgent.onStartSession(context, "QSTXAR19BLSUHANQ7BGM");
                flurryStarted = true;
            } else {
                getLogger().warning("Flurry disabled because no webview db or files not writable");
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem with flurry: ", th);
        }
        try {
            Yozio.YOZIO_ENABLE_LOGGING = false;
            Yozio.setAppKeyAndSecretKey(context, "42aa9263-f43d-4767-8f85-32ebd9e1954d", "e1ea3a52-a45b-4cf5-b02c-d42fd8f6972b");
        } catch (Throwable th2) {
            getLogger().log(Level.SEVERE, "problem with yozio: ", th2);
        }
    }

    private boolean canManipulateFiles(Context context) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            File fileStreamPath = getApp(context).getFileStreamPath(".flurryTEST.");
            if (fileStreamPath == null) {
                if (0 == 0) {
                    return false;
                }
                try {
                    fileOutputStream2.close();
                    return false;
                } catch (Throwable th) {
                    return false;
                }
            }
            File parentFile = fileStreamPath.getParentFile();
            if (parentFile == null) {
                if (0 == 0) {
                    return false;
                }
                try {
                    fileOutputStream2.close();
                    return false;
                } catch (Throwable th2) {
                    return false;
                }
            }
            parentFile.mkdirs();
            if (!parentFile.exists()) {
                if (0 == 0) {
                    return false;
                }
                try {
                    fileOutputStream2.close();
                    return false;
                } catch (Throwable th3) {
                    return false;
                }
            }
            FileOutputStream fileOutputStream3 = new FileOutputStream(fileStreamPath);
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream3);
                dataOutputStream.writeInt(1);
                dataOutputStream.writeUTF("foo");
                dataOutputStream.close();
                fileStreamPath.delete();
                if (fileOutputStream3 != null) {
                    try {
                        fileOutputStream3.close();
                    } catch (Throwable th4) {
                    }
                }
                return true;
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream2 = fileOutputStream3;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Throwable th6) {
                    }
                }
                throw th;
            }
        } catch (Throwable th7) {
            th = th7;
        }
    }

    @Override // com.thuuz.gtv.PlatformOperations
    public void appOnStart(Context context) {
    }

    private String getSysInfo(Context context) {
        try {
            App app = getApp(context);
            if (app != null) {
                return app.aj();
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return "";
    }

    private App getApp(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override // com.thuuz.gtv.PlatformOperations
    public void appOnStop(Context context) {
        try {
            if (flurryStarted) {
                FlurryAgent.onEndSession(context);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem with flurry: ", th);
        }
    }

    @Override // com.thuuz.gtv.PlatformOperations
    public void sendEvent(Context context, int i) {
        switch (i) {
            case 1:
            default:
                return;
        }
    }
}
