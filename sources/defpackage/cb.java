package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.UUID;

/* loaded from: classes.dex */
public class cb {
    private static String a = null;

    public static synchronized String a(Context context) {
        String str;
        synchronized (cb.class) {
            if (a == null) {
                File file = new File(context.getFilesDir(), "AF_INSTALLATION");
                try {
                    if (!file.exists()) {
                        a(file, context);
                    }
                    a = a(file);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            str = a;
        }
        return str;
    }

    private static String a(File file) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[(int) randomAccessFile.length()];
        randomAccessFile.readFully(bArr);
        randomAccessFile.close();
        return new String(bArr);
    }

    private static void a(File file, Context context) {
        String uuid;
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        if (Build.VERSION.SDK_INT >= 9) {
            uuid = packageInfo.firstInstallTime + "-" + Math.abs(new Random().nextLong());
        } else {
            uuid = UUID.randomUUID().toString();
        }
        fileOutputStream.write(uuid.getBytes());
        fileOutputStream.close();
    }
}
