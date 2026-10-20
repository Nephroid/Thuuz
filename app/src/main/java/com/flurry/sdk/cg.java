package com.flurry.sdk;

import android.os.Looper;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

/* loaded from: classes.dex */
public class cg {
    private static final String d = cg.class.getSimpleName();
    String a;
    long b = -1;
    int c = -1;
    private File e;

    public cg() {
        this.a = null;
        this.e = null;
        this.a = UUID.randomUUID().toString();
        this.e = eg.a().b().getFileStreamPath(".flurrydatasenderblock." + this.a);
    }

    public cg(String str) {
        this.a = null;
        this.e = null;
        this.a = str;
        this.e = eg.a().b().getFileStreamPath(".flurrydatasenderblock." + this.a);
    }

    public String a() {
        return this.a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v7 */
    public boolean a(byte[] bArr) {
        DataOutputStream dataOutputStream;
        boolean z = false;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ex.a(6, d, "setData(byte[]) running on the MAIN thread!");
        }
        ?? r2 = d;
        ex.a(4, (String) r2, "Writing FlurryDataSenderBlockInfo: " + this.e.getAbsolutePath());
        try {
            try {
            } catch (Throwable th) {
                th = th;
                fh.a((Closeable) r2);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            dataOutputStream = null;
        }
        if (et.a(this.e)) {
            dataOutputStream = new DataOutputStream(new FileOutputStream(this.e));
            try {
                int length = bArr.length;
                dataOutputStream.writeShort(length);
                dataOutputStream.write(bArr);
                dataOutputStream.writeShort(0);
                z = true;
                this.c = length;
                this.b = System.currentTimeMillis();
                fh.a(dataOutputStream);
                r2 = dataOutputStream;
            } catch (Throwable th3) {
                th = th3;
                ex.a(6, d, "", th);
                fh.a(dataOutputStream);
                r2 = dataOutputStream;
                return z;
            }
            return z;
        }
        fh.a((Closeable) null);
        return z;
    }

    public byte[] b() {
        DataInputStream dataInputStream;
        Throwable th;
        byte[] bArr = null;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ex.a(6, d, "getData() running on the MAIN thread!");
        }
        if (this.e.exists()) {
            ex.a(4, d, "Reading FlurryDataSenderBlockInfo: " + this.e.getAbsolutePath());
            try {
                dataInputStream = new DataInputStream(new FileInputStream(this.e));
                try {
                    try {
                        int readUnsignedShort = dataInputStream.readUnsignedShort();
                        if (readUnsignedShort == 0) {
                            fh.a(dataInputStream);
                        } else {
                            bArr = new byte[readUnsignedShort];
                            dataInputStream.readFully(bArr);
                            if (dataInputStream.readUnsignedShort() == 0) {
                            }
                            fh.a(dataInputStream);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        ex.a(6, d, "Error when loading persistent file", th);
                        fh.a(dataInputStream);
                        return bArr;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fh.a(dataInputStream);
                    throw th;
                }
            } catch (Throwable th4) {
                dataInputStream = null;
                th = th4;
                fh.a(dataInputStream);
                throw th;
            }
        } else {
            ex.a(4, d, "Agent cache file doesn't exist.");
        }
        return bArr;
    }

    public boolean c() {
        if (this.e.exists()) {
            if (this.e.delete()) {
                ex.a(4, d, "Deleted persistence file");
                this.b = -1L;
                this.c = -1;
                return true;
            }
            ex.a(6, d, "Cannot delete persistence file");
        }
        return false;
    }
}
