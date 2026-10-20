package com.thuuz.tv;

import android.os.Environment;
import android.os.StatFs;
// [removed legacy tracker import]
// [removed legacy tracker import]
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class TVDeviceManager {
    public static final Pattern a = Pattern.compile("i\\d+");
    public static final Logger b = Logger.getLogger("thuuz_TVDeviceManager");
    public static final String[] c = {"libgnustl_shared.so", "libexpat.so", "libext2_uuid.so", "libcybergarage.so", "libjsoncpp.so", "libctvtest.so"};
    private static boolean f = false;
    private static boolean h;
    private final File d;
    private final Logger e;
    private String g;

    private native String getChannelList(String str);

    public native String attachDevice(String str);

    public native String changeChannel(String str);

    public native String discoverAllDevices();

    public native String discoverDevices(String str);

    public native String finishPairing(String str);

    public native String getDeviceStatus(String str);

    public native boolean isProviderSupported(String str);

    public native String makeFolder(String str);

    public native String recordChannel(String str);

    public native String rediscoverDevice(String str);

    public native String startPairing(String str);

    public native String stringFromJNI(String str);

    public TVDeviceManager(File file, Logger logger, String str) {
        this.g = "www.thuuz.com";
        this.d = file;
        this.e = logger;
        this.g = str;
    }

    public List<String> a(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(getChannelList(str));
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
        } catch (Throwable th) {
            f().log(Level.SEVERE, "Cannot get channels: ", th);
        }
        return arrayList;
    }

    public boolean a() {
        return a(false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0043, code lost:
    
        if (a(r10.d) == false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(boolean r11) {
        /*
            r10 = this;
            r1 = 0
            r0 = 1
            boolean r2 = com.thuuz.tv.TVDeviceManager.h
            if (r2 == 0) goto L9
            if (r11 != 0) goto L9
        L8:
            return r0
        L9:
            boolean r2 = com.thuuz.tv.TVDeviceManager.f
            if (r2 == 0) goto L3b
            boolean r2 = com.thuuz.tv.TVDeviceManager.h
            if (r2 != 0) goto L3b
            java.util.logging.Logger r1 = r10.f()
            java.lang.String r2 = "using STATIC library loading for debugging"
            r1.info(r2)
            java.lang.String r1 = "gnustl_shared"
            java.lang.System.loadLibrary(r1)
            java.lang.String r1 = "expat"
            java.lang.System.loadLibrary(r1)
            java.lang.String r1 = "ext2_uuid"
            java.lang.System.loadLibrary(r1)
            java.lang.String r1 = "cybergarage"
            java.lang.System.loadLibrary(r1)
            java.lang.String r1 = "jsoncpp"
            java.lang.System.loadLibrary(r1)
            java.lang.String r1 = "ctvtest"
            java.lang.System.loadLibrary(r1)
            com.thuuz.tv.TVDeviceManager.h = r0
            goto L8
        L3b:
            if (r11 != 0) goto L45
            java.io.File r2 = r10.d     // Catch: java.lang.Throwable -> La3
            boolean r2 = a(r2)     // Catch: java.lang.Throwable -> La3
            if (r2 != 0) goto L4d
        L45:
            boolean r2 = r10.d()     // Catch: java.lang.Throwable -> La3
            if (r2 != 0) goto L4d
            r0 = r1
            goto L8
        L4d:
            java.io.File r2 = r10.d     // Catch: java.lang.Throwable -> La3
            java.io.File r3 = c(r2)     // Catch: java.lang.Throwable -> La3
            java.lang.String[] r4 = com.thuuz.tv.TVDeviceManager.c     // Catch: java.lang.Throwable -> La3
            int r5 = r4.length     // Catch: java.lang.Throwable -> La3
            r2 = r1
        L57:
            if (r2 >= r5) goto L9e
            r6 = r4[r2]     // Catch: java.lang.Throwable -> La3
            java.io.File r7 = new java.io.File     // Catch: java.lang.Throwable -> La3
            r7.<init>(r3, r6)     // Catch: java.lang.Throwable -> La3
            java.lang.String r6 = r7.getAbsolutePath()     // Catch: java.lang.Throwable -> La3
            java.util.logging.Logger r7 = r10.f()     // Catch: java.lang.Throwable -> La3
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La3
            r8.<init>()     // Catch: java.lang.Throwable -> La3
            java.lang.String r9 = "loading: "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Throwable -> La3
            java.lang.StringBuilder r8 = r8.append(r6)     // Catch: java.lang.Throwable -> La3
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> La3
            r7.info(r8)     // Catch: java.lang.Throwable -> La3
            java.lang.System.load(r6)     // Catch: java.lang.Throwable -> La3
            java.util.logging.Logger r7 = r10.f()     // Catch: java.lang.Throwable -> La3
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La3
            r8.<init>()     // Catch: java.lang.Throwable -> La3
            java.lang.String r9 = "loaded: "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Throwable -> La3
            java.lang.StringBuilder r6 = r8.append(r6)     // Catch: java.lang.Throwable -> La3
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> La3
            r7.info(r6)     // Catch: java.lang.Throwable -> La3
            int r2 = r2 + 1
            goto L57
        L9e:
            r2 = 1
            com.thuuz.tv.TVDeviceManager.h = r2     // Catch: java.lang.Throwable -> La3
            goto L8
        La3:
            r0 = move-exception
            java.util.logging.Logger r2 = r10.f()
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            java.lang.String r4 = "problem loading: "
            r2.log(r3, r4, r0)
            r0 = r1
            goto L8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.tv.TVDeviceManager.a(boolean):boolean");
    }

    private boolean d() {
        if (this.d == null) {
            f().severe("null files dir");
            return false;
        }
        String str = "http://" + this.g + "/static/app/android/ctv/v3" + e() + "/manifest.json";
        f().info("downloading manifest from: " + str);
        String b2 = b(str);
        if (b2 == null) {
            f().warning("cannot download .so manifest");
            return false;
        }
        JSONObject jSONObject = new JSONObject(b2);
        if (!jSONObject.has("base_url") || !jSONObject.has("files")) {
            f().severe(".so manifest lacks base_url or files: " + b2);
            return false;
        }
        String string = jSONObject.getString("base_url");
        HashMap hashMap = new HashMap();
        JSONObject jSONObject2 = jSONObject.getJSONObject("files");
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject2.getString(next));
        }
        String a2 = a(this.d, string, hashMap);
        if (a2 != null) {
            f().severe(a2);
            return false;
        }
        return true;
    }

    private String e() {
        String property = System.getProperty("os.arch");
        if (property == null) {
            return "_arm";
        }
        String lowerCase = property.toLowerCase();
        if (lowerCase.startsWith("arm")) {
            return "_arm";
        }
        if (lowerCase.startsWith("mips")) {
            return "_mips";
        }
        if (a.matcher(lowerCase).matches()) {
            return "_arm";
        }
        f().severe("non-arm arch: " + lowerCase);
        return "_arm";
    }

    public String a(File file, String str, Map<String, String> map) {
        return a(file, str, map, false);
    }

    public String a(File file, String str, Map<String, String> map, boolean z) {
        try {
            File c2 = c(file);
            if (c2 == null || (!c2.exists() && !c2.mkdirs())) {
                return "Cannot create directory for remote control: " + c2;
            }
            for (String str2 : c) {
                File file2 = new File(c2, str2);
                String str3 = map.get(str2);
                if (str3 == null) {
                    f().log(Level.SEVERE, "manifest hash missing for: " + str2);
                    return "Problem downloading";
                }
                if (file2.exists()) {
                    if (str3.equals(b(file2))) {
                        continue;
                    } else {
                        f().info("removing old file: " + file2.getCanonicalPath());
                        file2.delete();
                    }
                }
                URL url = new URL(str + str2);
                File file3 = new File(file, "temp_" + System.currentTimeMillis());
                if (file3.exists()) {
                    file3.delete();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                f().info("downloading: " + url);
                if (!a(url, fileOutputStream)) {
                    String str4 = "Cannot download remote control feature";
                    long c3 = c();
                    if (c3 < 30) {
                        str4 = "Cannot download remote control feature: free space only " + c3 + "K";
                    }
                    f().warning(str4);
                    if (file3.exists()) {
                        file3.delete();
                    }
                    if (!z) {
                        return null;
                    }
                    return str4;
                }
                String b2 = b(file3);
                if (!str3.equals(b2)) {
                    f().log(Level.SEVERE, "manifest hash: " + str3 + " != downloaded file hash: " + b2 + " for: " + str2);
                    file3.delete();
                    return "Problem downloading";
                }
                if (!file3.renameTo(file2)) {
                    a(file3, file2);
                }
            }
            return null;
        } catch (Throwable th) {
            return "problem copying libraries: " + th.toString();
        }
    }

    private void a(File file, File file2) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream = null;
        try {
            f().info("moving file: " + file.getCanonicalPath() + " to: " + file2.getCanonicalPath());
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream2.read(bArr);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileInputStream2.close();
                            try {
                                fileOutputStream.close();
                                file.delete();
                                return;
                            } catch (Throwable th) {
                                th = th;
                                f().log(Level.SEVERE, "problem: ", th);
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (Throwable th2) {
                                        f().log(Level.SEVERE, "problem: ", th2);
                                        return;
                                    }
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                    return;
                                }
                                return;
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = fileInputStream2;
                }
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
                fileInputStream = fileInputStream2;
            }
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = null;
        }
    }

    private static File c(File file) {
        if (file == null) {
            return null;
        }
        return new File(file, "armeabi");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0081 A[Catch: Throwable -> 0x0085, TRY_LEAVE, TryCatch #8 {Throwable -> 0x0085, blocks: (B:36:0x007c, B:30:0x0081), top: B:35:0x007c }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(java.net.URL r8, java.io.OutputStream r9) {
        /*
            r3 = 0
            r0 = 0
            java.net.URLConnection r1 = r8.openConnection()     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> L99
            r1.connect()     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> L99
            java.io.InputStream r4 = r1.getInputStream()     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> L99
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L90 java.lang.Throwable -> L9c
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L90 java.lang.Throwable -> L9c
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L22 java.lang.Throwable -> L94
            r1.<init>(r9)     // Catch: java.lang.Throwable -> L22 java.lang.Throwable -> L94
        L17:
            int r3 = r2.read()     // Catch: java.lang.Throwable -> L22 java.lang.Throwable -> L94
            r5 = -1
            if (r3 == r5) goto L4d
            r1.write(r3)     // Catch: java.lang.Throwable -> L22 java.lang.Throwable -> L94
            goto L17
        L22:
            r1 = move-exception
            r3 = r4
        L24:
            java.util.logging.Logger r4 = com.thuuz.tv.TVDeviceManager.b     // Catch: java.lang.Throwable -> L97
            java.util.logging.Level r5 = java.util.logging.Level.WARNING     // Catch: java.lang.Throwable -> L97
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L97
            r6.<init>()     // Catch: java.lang.Throwable -> L97
            java.lang.String r7 = "Error downloading file: "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch: java.lang.Throwable -> L97
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L97
            java.lang.StringBuilder r1 = r6.append(r1)     // Catch: java.lang.Throwable -> L97
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L97
            r4.log(r5, r1)     // Catch: java.lang.Throwable -> L97
            if (r2 == 0) goto L47
            r2.close()     // Catch: java.lang.Throwable -> L6d
        L47:
            if (r3 == 0) goto L4c
            r3.close()     // Catch: java.lang.Throwable -> L6d
        L4c:
            return r0
        L4d:
            r1.close()     // Catch: java.lang.Throwable -> L22 java.lang.Throwable -> L94
            r2.close()     // Catch: java.lang.Throwable -> L22 java.lang.Throwable -> L94
            r4.close()     // Catch: java.lang.Throwable -> L22 java.lang.Throwable -> L94
            r0 = 1
            if (r2 == 0) goto L5c
            r2.close()     // Catch: java.lang.Throwable -> L62
        L5c:
            if (r4 == 0) goto L4c
            r4.close()     // Catch: java.lang.Throwable -> L62
            goto L4c
        L62:
            r1 = move-exception
            java.util.logging.Logger r2 = com.thuuz.tv.TVDeviceManager.b
            java.util.logging.Level r3 = java.util.logging.Level.WARNING
            java.lang.String r4 = "cannot close file download streams : "
            r2.log(r3, r4, r1)
            goto L4c
        L6d:
            r1 = move-exception
            java.util.logging.Logger r2 = com.thuuz.tv.TVDeviceManager.b
            java.util.logging.Level r3 = java.util.logging.Level.WARNING
            java.lang.String r4 = "cannot close file download streams : "
            r2.log(r3, r4, r1)
            goto L4c
        L78:
            r0 = move-exception
            r2 = r3
        L7a:
            if (r2 == 0) goto L7f
            r2.close()     // Catch: java.lang.Throwable -> L85
        L7f:
            if (r3 == 0) goto L84
            r3.close()     // Catch: java.lang.Throwable -> L85
        L84:
            throw r0
        L85:
            r1 = move-exception
            java.util.logging.Logger r2 = com.thuuz.tv.TVDeviceManager.b
            java.util.logging.Level r3 = java.util.logging.Level.WARNING
            java.lang.String r4 = "cannot close file download streams : "
            r2.log(r3, r4, r1)
            goto L84
        L90:
            r0 = move-exception
            r2 = r3
            r3 = r4
            goto L7a
        L94:
            r0 = move-exception
            r3 = r4
            goto L7a
        L97:
            r0 = move-exception
            goto L7a
        L99:
            r1 = move-exception
            r2 = r3
            goto L24
        L9c:
            r1 = move-exception
            r2 = r3
            r3 = r4
            goto L24
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thuuz.tv.TVDeviceManager.a(java.net.URL, java.io.OutputStream):boolean");
    }

    public String b() {
        File file = new File(this.d, "certs");
        if (!file.exists() && !file.mkdirs()) {
            b.severe("cannot create: " + file.getAbsolutePath());
        }
        return file.getAbsolutePath();
    }

    public long c() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return (statFs.getAvailableBlocks() * statFs.getBlockSize()) / 1024;
        } catch (Throwable th) {
            b.log(Level.SEVERE, "problem getting free space: ", th);
            return 2000L;
        }
    }

    public static boolean a(File file) {
        try {
            File c2 = c(file);
            for (String str : c) {
                if (!new File(c2, str).exists()) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            b.log(Level.SEVERE, "problem: ", th);
            return false;
        }
    }

    public static String b(File file) {
        try {
            if (!file.exists()) {
                return "";
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bArr = new byte[8192];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    messageDigest.update(bArr, 0, read);
                } else {
                    return a(messageDigest.digest());
                }
            }
        } catch (Throwable th) {
            b.log(Level.SEVERE, "md5 issue: ", th);
            return "";
        }
    }

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            int i = b2 & Constants.UNKNOWN;
            if (i < 16) {
                stringBuffer.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            stringBuffer.append(Integer.toHexString(i));
        }
        return stringBuffer.toString();
    }

    private String b(String str) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (a(new URL(str), byteArrayOutputStream)) {
                return byteArrayOutputStream.toString("utf-8");
            }
            return null;
        } catch (Throwable th) {
            b.log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }

    private Logger f() {
        return this.e;
    }
}
