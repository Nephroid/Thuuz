package com.flurry.sdk;

import android.os.Looper;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class ci {
    String b;
    LinkedHashMap<String, List<String>> c;
    private static final String d = ci.class.getSimpleName();
    static final Integer a = 50;

    public ci(String str) {
        a(str);
    }

    void a(String str) {
        this.c = new LinkedHashMap<>();
        this.b = str + "Main";
        List<String> e = e(this.b);
        if (e != null) {
            for (String str2 : e) {
                List<String> e2 = e(str2);
                if (e2 != null) {
                    this.c.put(str2, e2);
                }
            }
        }
    }

    private synchronized void c() {
        LinkedList linkedList = new LinkedList(this.c.keySet());
        b();
        if (!linkedList.isEmpty()) {
            a(this.b, linkedList);
        }
    }

    public synchronized void a(cg cgVar, String str) {
        List<String> list;
        boolean z = false;
        synchronized (this) {
            ex.a(4, d, "addBlockInfo");
            String a2 = cgVar.a();
            List<String> list2 = this.c.get(str);
            if (list2 == null) {
                ex.a(4, d, "New Data Key");
                list = new LinkedList();
                z = true;
            } else {
                list = list2;
            }
            list.add(a2);
            if (list.size() > a.intValue()) {
                b(list.get(0));
                list.remove(0);
            }
            this.c.put(str, list);
            a(str, list);
            if (z) {
                c();
            }
        }
    }

    boolean b(String str) {
        return new cg(str).c();
    }

    public boolean a(String str, String str2) {
        List<String> list = this.c.get(str2);
        boolean z = false;
        if (list != null) {
            b(str);
            z = list.remove(str);
        }
        if (list != null && !list.isEmpty()) {
            this.c.put(str2, list);
            a(str2, list);
        } else {
            d(str2);
        }
        return z;
    }

    public List<String> a() {
        return new ArrayList(this.c.keySet());
    }

    public List<String> c(String str) {
        return this.c.get(str);
    }

    public synchronized boolean d(String str) {
        boolean a2;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ex.a(6, d, "discardOutdatedBlocksForDataKey(ID) running on the MAIN thread!");
        }
        File fileStreamPath = eg.a().b().getFileStreamPath(".FlurrySenderIndex.info." + str);
        List<String> c = c(str);
        if (c != null) {
            ex.a(4, d, "discardOutdatedBlocksForDataKey: notSentBlocks = " + c.size());
            for (int i = 0; i < c.size(); i++) {
                String str2 = c.get(i);
                b(str2);
                ex.a(4, d, "discardOutdatedBlocksForDataKey: removed block = " + str2);
            }
        }
        this.c.remove(str);
        a2 = a(fileStreamPath);
        c();
        return a2;
    }

    private synchronized boolean a(File file) {
        boolean z;
        z = false;
        if (file != null) {
            if (file.exists()) {
                ex.a(4, d, "Trying to delete persistence file : " + file.getAbsolutePath());
                z = file.delete();
                if (z) {
                    ex.a(4, d, "Deleted persistence file");
                } else {
                    ex.a(6, d, "Cannot delete persistence file");
                }
            }
        }
        return z;
    }

    void b() {
        a(eg.a().b().getFileStreamPath(".FlurrySenderIndex.info." + this.b));
    }

    private synchronized List<String> e(String str) {
        DataInputStream dataInputStream;
        ArrayList arrayList;
        Throwable th;
        int readUnsignedShort;
        ArrayList arrayList2 = null;
        synchronized (this) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                ex.a(6, d, "readFromFile(byte[], ID) running on the MAIN thread!");
            }
            File fileStreamPath = eg.a().b().getFileStreamPath(".FlurrySenderIndex.info." + str);
            if (fileStreamPath.exists()) {
                try {
                    dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
                    try {
                        try {
                            readUnsignedShort = dataInputStream.readUnsignedShort();
                        } catch (Throwable th2) {
                            arrayList = null;
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fh.a(dataInputStream);
                        throw th;
                    }
                } catch (Throwable th4) {
                    dataInputStream = null;
                    arrayList = null;
                    th = th4;
                }
                if (readUnsignedShort == 0) {
                    fh.a(dataInputStream);
                } else {
                    arrayList = new ArrayList(readUnsignedShort);
                    for (int i = 0; i < readUnsignedShort; i++) {
                        try {
                            int readUnsignedShort2 = dataInputStream.readUnsignedShort();
                            ex.a(4, d, "read iter " + i + " dataLength = " + readUnsignedShort2);
                            byte[] bArr = new byte[readUnsignedShort2];
                            dataInputStream.readFully(bArr);
                            arrayList.add(new String(bArr));
                        } catch (Throwable th5) {
                            th = th5;
                            ex.a(6, d, "Error when loading persistent file", th);
                            fh.a(dataInputStream);
                            arrayList2 = arrayList;
                            return arrayList2;
                        }
                    }
                    if (dataInputStream.readUnsignedShort() == 0) {
                    }
                    fh.a(dataInputStream);
                }
            } else {
                ex.a(5, d, "Agent cache file doesn't exist.");
                arrayList = null;
            }
            arrayList2 = arrayList;
        }
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.Closeable] */
    private synchronized boolean a(String str, List<String> list) {
        DataOutputStream dataOutputStream;
        boolean z;
        boolean z2 = false;
        synchronized (this) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                ex.a(6, d, "saveToFile(byte[], ID) running on the MAIN thread!");
            }
            ?? r2 = ".FlurrySenderIndex.info." + str;
            File fileStreamPath = eg.a().b().getFileStreamPath(r2);
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
            if (et.a(fileStreamPath)) {
                dataOutputStream = new DataOutputStream(new FileOutputStream(fileStreamPath));
                try {
                    dataOutputStream.writeShort(list.size());
                    for (int i = 0; i < list.size(); i++) {
                        byte[] bytes = list.get(i).getBytes();
                        int length = bytes.length;
                        ex.a(4, d, "write iter " + i + " dataLength = " + length);
                        dataOutputStream.writeShort(length);
                        dataOutputStream.write(bytes);
                    }
                    dataOutputStream.writeShort(0);
                    z = true;
                    fh.a(dataOutputStream);
                } catch (Throwable th3) {
                    th = th3;
                    ex.a(6, d, "", th);
                    fh.a(dataOutputStream);
                    z = false;
                    z2 = z;
                    return z2;
                }
                z2 = z;
            } else {
                fh.a((Closeable) null);
            }
        }
        return z2;
    }
}
