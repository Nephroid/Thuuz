package defpackage;

import com.android.volley.Request;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public abstract class bk<T> extends Request<T> {
    private static final String a = String.format("application/json; charset=%s", "utf-8");
    private final aj<T> b;
    private final String c;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public abstract ah<T> a(ae aeVar);

    public bk(int i, String str, String str2, aj<T> ajVar, ai aiVar) {
        super(i, str, aiVar);
        this.b = ajVar;
        this.c = str2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public void a(T t) {
        this.b.a(t);
    }

    @Override // com.android.volley.Request
    public String k() {
        return o();
    }

    @Override // com.android.volley.Request
    public byte[] l() {
        return p();
    }

    @Override // com.android.volley.Request
    public String o() {
        return a;
    }

    @Override // com.android.volley.Request
    public byte[] p() {
        try {
            if (this.c == null) {
                return null;
            }
            return this.c.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            am.d("Unsupported Encoding while trying to get the bytes of %s using %s", this.c, "utf-8");
            return null;
        }
    }
}
