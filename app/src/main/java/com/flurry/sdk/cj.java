package com.flurry.sdk;

import com.flurry.sdk.bx;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class cj {
    private static final String b = cj.class.getSimpleName();
    byte[] a;

    public cj(byte[] bArr) {
        this.a = bArr;
    }

    public cj(ck ckVar) {
        DataOutputStream dataOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        int i;
        DataOutputStream dataOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        } catch (IOException e) {
            e = e;
        } catch (Throwable th) {
            th = th;
            dataOutputStream = null;
        }
        try {
            dataOutputStream.writeShort(1);
            dataOutputStream.writeUTF(ckVar.a());
            dataOutputStream.writeLong(ckVar.b());
            dataOutputStream.writeLong(ckVar.c());
            dataOutputStream.writeLong(ckVar.d());
            dataOutputStream.writeUTF(ckVar.e());
            dataOutputStream.writeUTF(ckVar.f());
            dataOutputStream.writeByte(ckVar.g());
            dataOutputStream.writeUTF(ckVar.h());
            if (ckVar.i() == null) {
                dataOutputStream.writeBoolean(false);
            } else {
                dataOutputStream.writeBoolean(true);
                dataOutputStream.writeDouble(a(ckVar.i().getLatitude()));
                dataOutputStream.writeDouble(a(ckVar.i().getLongitude()));
                dataOutputStream.writeFloat(ckVar.i().getAccuracy());
            }
            dataOutputStream.writeInt(ckVar.j());
            dataOutputStream.writeByte(-1);
            dataOutputStream.writeByte(-1);
            dataOutputStream.writeByte(ckVar.k());
            if (ckVar.l() == null) {
                dataOutputStream.writeBoolean(false);
            } else {
                dataOutputStream.writeBoolean(true);
                dataOutputStream.writeLong(ckVar.l().longValue());
            }
            Map<String, bx.a> m = ckVar.m();
            if (m == null) {
                dataOutputStream.writeShort(0);
            } else {
                dataOutputStream.writeShort(m.size());
                for (Map.Entry<String, bx.a> entry : m.entrySet()) {
                    dataOutputStream.writeUTF(entry.getKey());
                    dataOutputStream.writeInt(entry.getValue().a);
                }
            }
            List<cb> n = ckVar.n();
            if (n == null) {
                dataOutputStream.writeShort(0);
            } else {
                dataOutputStream.writeShort(n.size());
                Iterator<cb> it = n.iterator();
                while (it.hasNext()) {
                    dataOutputStream.write(it.next().e());
                }
            }
            dataOutputStream.writeBoolean(ckVar.o());
            List<ca> q = ckVar.q();
            if (q != null) {
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    if (i2 >= q.size()) {
                        i = i4;
                        break;
                    }
                    i3 += q.get(i2).a();
                    if (i3 > 160000) {
                        ex.a(5, b, "Error Log size exceeded. No more event details logged.");
                        i = i4;
                        break;
                    } else {
                        i4++;
                        i2++;
                    }
                }
            } else {
                i = 0;
            }
            dataOutputStream.writeInt(ckVar.p());
            dataOutputStream.writeShort(i);
            for (int i5 = 0; i5 < i; i5++) {
                dataOutputStream.write(q.get(i5).b());
            }
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            this.a = byteArrayOutputStream.toByteArray();
            fh.a(dataOutputStream);
        } catch (IOException e2) {
            e = e2;
            dataOutputStream2 = dataOutputStream;
            try {
                ex.a(6, b, "", e);
                throw e;
            } catch (Throwable th2) {
                th = th2;
                dataOutputStream = dataOutputStream2;
                fh.a(dataOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fh.a(dataOutputStream);
            throw th;
        }
    }

    public byte[] a() {
        return this.a;
    }

    double a(double d) {
        return Math.round(d * 1000.0d) / 1000.0d;
    }
}
