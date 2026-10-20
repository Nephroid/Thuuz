package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.AppEventsConstants;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/* loaded from: classes.dex */
class em implements fp {
    private static Object a = new Object();
    private static em b;
    private dk c;
    private long d;
    private Context e;
    private String f;
    private boolean g = false;
    private Object h = new Object();

    em(Context context) {
        this.e = context.getApplicationContext();
    }

    public static fp a(Context context) {
        if (b == null) {
            synchronized (a) {
                if (b == null) {
                    b = new em(context);
                }
            }
        }
        return b;
    }

    private boolean a(dk dkVar, dk dkVar2) {
        String str;
        String a2 = dkVar2 == null ? null : dkVar2.a();
        if (TextUtils.isEmpty(a2)) {
            return true;
        }
        fj.a(this.e);
        fj a3 = fj.a();
        String a4 = a3.a("&cid");
        synchronized (this.h) {
            if (!this.g) {
                this.f = b(this.e);
                this.g = true;
            } else if (TextUtils.isEmpty(this.f)) {
                String a5 = dkVar != null ? dkVar.a() : null;
                if (a5 == null) {
                    return c(a2 + a4);
                }
                this.f = b(a5 + a4);
            }
            String b2 = b(a2 + a4);
            if (TextUtils.isEmpty(b2)) {
                return false;
            }
            if (b2.equals(this.f)) {
                return true;
            }
            if (TextUtils.isEmpty(this.f)) {
                str = a4;
            } else {
                gn.c("Resetting the client id because Advertising Id changed.");
                str = a3.b();
                gn.c("New client Id: " + str);
            }
            return c(a2 + str);
        }
    }

    static String b(Context context) {
        String str = null;
        try {
            FileInputStream openFileInput = context.openFileInput("gaClientIdData");
            byte[] bArr = new byte[128];
            int read = openFileInput.read(bArr, 0, 128);
            if (openFileInput.available() > 0) {
                gn.d("Hash file seems corrupted, deleting it.");
                openFileInput.close();
                context.deleteFile("gaClientIdData");
            } else if (read <= 0) {
                gn.b("Hash file is empty.");
                openFileInput.close();
            } else {
                String str2 = new String(bArr, 0, read);
                try {
                    openFileInput.close();
                    str = str2;
                } catch (FileNotFoundException e) {
                    str = str2;
                } catch (IOException e2) {
                    str = str2;
                    gn.d("Error reading Hash file, deleting it.");
                    context.deleteFile("gaClientIdData");
                    return str;
                }
            }
        } catch (FileNotFoundException e3) {
        } catch (IOException e4) {
        }
        return str;
    }

    static String b(String str) {
        MessageDigest c = ew.c("MD5");
        if (c == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, c.digest(str.getBytes())));
    }

    private boolean c(String str) {
        try {
            String b2 = b(str);
            gn.c("Storing hashed adid.");
            FileOutputStream openFileOutput = this.e.openFileOutput("gaClientIdData", 0);
            openFileOutput.write(b2.getBytes());
            openFileOutput.close();
            this.f = b2;
            return true;
        } catch (FileNotFoundException e) {
            gn.a("Error creating hash file.");
            return false;
        } catch (IOException e2) {
            gn.a("Error writing to hash file.");
            return false;
        }
    }

    dk a() {
        try {
            return dj.b(this.e);
        } catch (GooglePlayServicesNotAvailableException e) {
            gn.d("GooglePlayServicesNotAvailableException getting Ad Id Info");
            return null;
        } catch (GooglePlayServicesRepairableException e2) {
            gn.d("GooglePlayServicesRepairableException getting Ad Id Info");
            return null;
        } catch (IOException e3) {
            gn.d("IOException getting Ad Id Info");
            return null;
        } catch (IllegalStateException e4) {
            gn.d("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
            return null;
        } catch (Throwable th) {
            gn.d("Unknown exception. Could not get the ad Id.");
            return null;
        }
    }

    @Override // defpackage.fp
    public String a(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.d > 1000) {
            dk a2 = a();
            if (a(this.c, a2)) {
                this.c = a2;
            } else {
                this.c = new dk("", false);
            }
            this.d = currentTimeMillis;
        }
        if (this.c != null) {
            if ("&adid".equals(str)) {
                return this.c.a();
            }
            if ("&ate".equals(str)) {
                return this.c.b() ? AppEventsConstants.EVENT_PARAM_VALUE_NO : AppEventsConstants.EVENT_PARAM_VALUE_YES;
            }
        }
        return null;
    }
}
