package defpackage;

import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public class as implements u {
    private final Map<String, au> a;
    private long b;
    private final File c;
    private final int d;

    public as(File file, int i) {
        this.a = new LinkedHashMap(16, 0.75f, true);
        this.b = 0L;
        this.c = file;
        this.d = i;
    }

    public as(File file) {
        this(file, 5242880);
    }

    @Override // defpackage.u
    public synchronized v a(String str) {
        av avVar;
        v vVar;
        File c;
        int i;
        au auVar = this.a.get(str);
        if (auVar == null) {
            vVar = null;
        } else {
            try {
                c = c(str);
            } catch (Throwable th) {
                th = th;
            }
            try {
                avVar = new av(new FileInputStream(c));
                try {
                    au.a(avVar);
                    long length = c.length();
                    i = avVar.a;
                    vVar = auVar.a(a(avVar, (int) (length - i)));
                    if (avVar != null) {
                        try {
                            avVar.close();
                        } catch (IOException e) {
                            vVar = null;
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    am.b("%s: %s", c.getAbsolutePath(), e.toString());
                    b(str);
                    if (avVar != null) {
                        try {
                            avVar.close();
                        } catch (IOException e3) {
                            vVar = null;
                        }
                    }
                    vVar = null;
                    return vVar;
                }
            } catch (IOException e4) {
                e = e4;
                avVar = null;
            } catch (Throwable th2) {
                th = th2;
                avVar = null;
                if (avVar != null) {
                    try {
                        avVar.close();
                    } catch (IOException e5) {
                        vVar = null;
                    }
                }
                throw th;
            }
        }
        return vVar;
    }

    @Override // defpackage.u
    public synchronized void a() {
        BufferedInputStream bufferedInputStream;
        if (!this.c.exists()) {
            if (!this.c.mkdirs()) {
                am.c("Unable to create cache dir %s", this.c.getAbsolutePath());
            }
        } else {
            File[] listFiles = this.c.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    BufferedInputStream bufferedInputStream2 = null;
                    try {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                        try {
                            try {
                                au a = au.a(bufferedInputStream);
                                a.a = file.length();
                                a(a.b, a);
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e) {
                                    }
                                }
                            } catch (IOException e2) {
                                if (file != null) {
                                    file.delete();
                                }
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e3) {
                                    }
                                }
                            }
                        } catch (Throwable th) {
                            bufferedInputStream2 = bufferedInputStream;
                            th = th;
                            if (bufferedInputStream2 != null) {
                                try {
                                    bufferedInputStream2.close();
                                } catch (IOException e4) {
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                        bufferedInputStream = null;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
        }
    }

    @Override // defpackage.u
    public synchronized void a(String str, v vVar) {
        FileOutputStream fileOutputStream;
        au auVar;
        a(vVar.a.length);
        File c = c(str);
        try {
            fileOutputStream = new FileOutputStream(c);
            auVar = new au(str, vVar);
        } catch (IOException e) {
            if (!c.delete()) {
                am.b("Could not clean up file %s", c.getAbsolutePath());
            }
        }
        if (!auVar.a(fileOutputStream)) {
            fileOutputStream.close();
            am.b("Failed to write header for %s", c.getAbsolutePath());
            throw new IOException();
        }
        fileOutputStream.write(vVar.a);
        fileOutputStream.close();
        a(str, auVar);
    }

    public synchronized void b(String str) {
        boolean delete = c(str).delete();
        e(str);
        if (!delete) {
            am.b("Could not delete cache entry for key=%s, filename=%s", str, d(str));
        }
    }

    private String d(String str) {
        int length = str.length() / 2;
        return String.valueOf(str.substring(0, length).hashCode()) + String.valueOf(str.substring(length).hashCode());
    }

    public File c(String str) {
        return new File(this.c, d(str));
    }

    private void a(int i) {
        int i2;
        if (this.b + i >= this.d) {
            if (am.b) {
                am.a("Pruning old cache entries.", new Object[0]);
            }
            long j = this.b;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, au>> it = this.a.entrySet().iterator();
            int i3 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i2 = i3;
                    break;
                }
                au value = it.next().getValue();
                if (c(value.b).delete()) {
                    this.b -= value.a;
                } else {
                    am.b("Could not delete cache entry for key=%s, filename=%s", value.b, d(value.b));
                }
                it.remove();
                i2 = i3 + 1;
                if (((float) (this.b + i)) < this.d * 0.9f) {
                    break;
                } else {
                    i3 = i2;
                }
            }
            if (am.b) {
                am.a("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.b - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        }
    }

    private void a(String str, au auVar) {
        if (!this.a.containsKey(str)) {
            this.b += auVar.a;
        } else {
            this.b = (auVar.a - this.a.get(str).a) + this.b;
        }
        this.a.put(str, auVar);
    }

    private void e(String str) {
        au auVar = this.a.get(str);
        if (auVar != null) {
            this.b -= auVar.a;
            this.a.remove(str);
        }
    }

    private static byte[] a(InputStream inputStream, int i) {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read == -1) {
                break;
            }
            i2 += read;
        }
        if (i2 != i) {
            throw new IOException("Expected " + i + " bytes, read " + i2 + " bytes");
        }
        return bArr;
    }

    private static int e(InputStream inputStream) {
        int read = inputStream.read();
        if (read == -1) {
            throw new EOFException();
        }
        return read;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(OutputStream outputStream, int i) {
        outputStream.write((i >> 0) & MotionEventCompat.ACTION_MASK);
        outputStream.write((i >> 8) & MotionEventCompat.ACTION_MASK);
        outputStream.write((i >> 16) & MotionEventCompat.ACTION_MASK);
        outputStream.write((i >> 24) & MotionEventCompat.ACTION_MASK);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(InputStream inputStream) {
        return 0 | (e(inputStream) << 0) | (e(inputStream) << 8) | (e(inputStream) << 16) | (e(inputStream) << 24);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(OutputStream outputStream, long j) {
        outputStream.write((byte) (j >>> 0));
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 32));
        outputStream.write((byte) (j >>> 40));
        outputStream.write((byte) (j >>> 48));
        outputStream.write((byte) (j >>> 56));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long b(InputStream inputStream) {
        return 0 | ((e(inputStream) & 255) << 0) | ((e(inputStream) & 255) << 8) | ((e(inputStream) & 255) << 16) | ((e(inputStream) & 255) << 24) | ((e(inputStream) & 255) << 32) | ((e(inputStream) & 255) << 40) | ((e(inputStream) & 255) << 48) | ((e(inputStream) & 255) << 56);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(OutputStream outputStream, String str) {
        byte[] bytes = str.getBytes(HTTP.UTF_8);
        a(outputStream, bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c(InputStream inputStream) {
        return new String(a(inputStream, (int) b(inputStream)), HTTP.UTF_8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Map<String, String> map, OutputStream outputStream) {
        if (map != null) {
            a(outputStream, map.size());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                a(outputStream, entry.getKey());
                a(outputStream, entry.getValue());
            }
            return;
        }
        a(outputStream, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, String> d(InputStream inputStream) {
        int a = a(inputStream);
        Map<String, String> emptyMap = a == 0 ? Collections.emptyMap() : new HashMap<>(a);
        for (int i = 0; i < a; i++) {
            emptyMap.put(c(inputStream).intern(), c(inputStream).intern());
        }
        return emptyMap;
    }
}
