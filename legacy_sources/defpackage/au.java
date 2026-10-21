package defpackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/* loaded from: classes.dex */
class au {
    public long a;
    public String b;
    public String c;
    public long d;
    public long e;
    public long f;
    public Map<String, String> g;

    private au() {
    }

    public au(String str, v vVar) {
        this.b = str;
        this.a = vVar.a.length;
        this.c = vVar.b;
        this.d = vVar.c;
        this.e = vVar.d;
        this.f = vVar.e;
        this.g = vVar.f;
    }

    public static au a(InputStream inputStream) {
        au auVar = new au();
        if (as.a(inputStream) != 538183203) {
            throw new IOException();
        }
        auVar.b = as.c(inputStream);
        auVar.c = as.c(inputStream);
        if (auVar.c.equals("")) {
            auVar.c = null;
        }
        auVar.d = as.b(inputStream);
        auVar.e = as.b(inputStream);
        auVar.f = as.b(inputStream);
        auVar.g = as.d(inputStream);
        return auVar;
    }

    public v a(byte[] bArr) {
        v vVar = new v();
        vVar.a = bArr;
        vVar.b = this.c;
        vVar.c = this.d;
        vVar.d = this.e;
        vVar.e = this.f;
        vVar.f = this.g;
        return vVar;
    }

    public boolean a(OutputStream outputStream) {
        try {
            as.a(outputStream, 538183203);
            as.a(outputStream, this.b);
            as.a(outputStream, this.c == null ? "" : this.c);
            as.a(outputStream, this.d);
            as.a(outputStream, this.e);
            as.a(outputStream, this.f);
            as.a(this.g, outputStream);
            outputStream.flush();
            return true;
        } catch (IOException e) {
            am.b("%s", e.toString());
            return false;
        }
    }
}
