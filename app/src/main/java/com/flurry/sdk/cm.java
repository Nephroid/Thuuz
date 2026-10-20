package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class cm {
    private static final String a = cm.class.getSimpleName();
    private boolean b;
    private List<cj> c;
    private long d = -1;

    public void a(boolean z) {
        this.b = z;
    }

    public boolean a() {
        return this.b;
    }

    public void a(List<cj> list) {
        this.c = list;
    }

    public List<cj> b() {
        return this.c;
    }

    public void a(long j) {
        this.d = j;
    }

    public long c() {
        return this.d;
    }

    public void a(DataOutputStream dataOutputStream, String str, String str2) {
        try {
            try {
                dataOutputStream.writeShort(46586);
                dataOutputStream.writeShort(2);
                dataOutputStream.writeUTF(str);
                dataOutputStream.writeUTF(str2);
                dataOutputStream.writeBoolean(this.b);
                dataOutputStream.writeLong(this.d);
                int size = this.c.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    byte[] a2 = this.c.get(size).a();
                    int length = a2.length;
                    if (length + 2 + dataOutputStream.size() > 50000) {
                        ex.a(6, a, "discarded sessions: " + size);
                        break;
                    } else {
                        dataOutputStream.writeShort(length);
                        dataOutputStream.write(a2);
                        size--;
                    }
                }
                dataOutputStream.writeShort(0);
            } catch (Throwable th) {
                ex.a(6, a, "", th);
                throw new IOException(th.getMessage());
            }
        } finally {
            fh.a(dataOutputStream);
        }
    }

    public boolean a(DataInputStream dataInputStream, String str) {
        boolean z = false;
        try {
            try {
                int readUnsignedShort = dataInputStream.readUnsignedShort();
                ex.a(4, a, "Magic: " + readUnsignedShort);
                if (readUnsignedShort == 46586) {
                    z = a(str, dataInputStream);
                } else {
                    ex.a(3, a, "Unexpected file type");
                }
                return z;
            } catch (Throwable th) {
                ex.a(6, a, "Error when loading persistent file", th);
                throw new IOException(th.getMessage());
            }
        } finally {
            fh.a(dataInputStream);
        }
    }

    private boolean a(String str, DataInputStream dataInputStream) {
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        ex.a(3, a, "File version: " + readUnsignedShort);
        if (readUnsignedShort > 2) {
            ex.a(6, a, "Unknown agent file version: " + readUnsignedShort);
            throw new IOException("Unknown agent file version: " + readUnsignedShort);
        }
        if (readUnsignedShort >= 2) {
            String readUTF = dataInputStream.readUTF();
            ex.a(3, a, "Loading API key: " + a(str));
            if (readUTF.equals(str)) {
                ArrayList arrayList = new ArrayList();
                dataInputStream.readUTF();
                boolean readBoolean = dataInputStream.readBoolean();
                long readLong = dataInputStream.readLong();
                ex.a(3, a, "Loading session reports");
                int i = 0;
                while (true) {
                    int readUnsignedShort2 = dataInputStream.readUnsignedShort();
                    if (readUnsignedShort2 != 0) {
                        byte[] bArr = new byte[readUnsignedShort2];
                        dataInputStream.readFully(bArr);
                        arrayList.add(0, new cj(bArr));
                        i++;
                        ex.a(3, a, "Session report added: " + i);
                    } else {
                        ex.a(3, a, "Persistent file loaded");
                        a(readBoolean);
                        a(readLong);
                        a(arrayList);
                        return true;
                    }
                }
            } else {
                ex.a(3, a, "Api keys do not match, old: " + a(str) + ", new: " + a(readUTF));
                return false;
            }
        } else {
            ex.a(5, a, "Deleting old file version: " + readUnsignedShort);
            return false;
        }
    }

    private static String a(String str) {
        if (str != null && str.length() > 4) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length() - 4; i++) {
                sb.append('*');
            }
            sb.append(str.substring(str.length() - 4));
            return sb.toString();
        }
        return str;
    }
}
