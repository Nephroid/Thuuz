package defpackage;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public abstract class aed implements aci {
    protected MotionEvent a;
    protected DisplayMetrics b;
    protected aht c;
    private aik d;

    /* JADX INFO: Access modifiers changed from: protected */
    public aed(Context context, aht ahtVar, aik aikVar) {
        this.c = ahtVar;
        this.d = aikVar;
        try {
            this.b = context.getResources().getDisplayMetrics();
        } catch (UnsupportedOperationException e) {
            this.b = new DisplayMetrics();
            this.b.density = 1.0f;
        }
    }

    private String a(Context context, String str, boolean z) {
        byte[] b;
        try {
            synchronized (this) {
                a();
                if (z) {
                    c(context);
                } else {
                    b(context);
                }
                b = b();
            }
            return b.length == 0 ? Integer.toString(5) : a(b, str);
        } catch (UnsupportedEncodingException e) {
            return Integer.toString(7);
        } catch (IOException e2) {
            return Integer.toString(3);
        } catch (NoSuchAlgorithmException e3) {
            return Integer.toString(7);
        }
    }

    private void a() {
        this.d.a();
    }

    private byte[] b() {
        return this.d.b();
    }

    @Override // defpackage.aci
    public String a(Context context) {
        return a(context, (String) null, false);
    }

    @Override // defpackage.aci
    public String a(Context context, String str) {
        return a(context, str, true);
    }

    String a(byte[] bArr, String str) {
        byte[] array;
        if (bArr.length > 239) {
            a();
            a(20, 1L);
            bArr = b();
        }
        if (bArr.length < 239) {
            byte[] bArr2 = new byte[239 - bArr.length];
            new SecureRandom().nextBytes(bArr2);
            array = ByteBuffer.allocate(240).put((byte) bArr.length).put(bArr).put(bArr2).array();
        } else {
            array = ByteBuffer.allocate(240).put((byte) bArr.length).put(bArr).array();
        }
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(array);
        byte[] array2 = ByteBuffer.allocate(256).put(messageDigest.digest()).put(array).array();
        byte[] bArr3 = new byte[256];
        new zz().a(array2, bArr3);
        if (str != null && str.length() > 0) {
            a(str, bArr3);
        }
        return this.c.a(bArr3, true);
    }

    @Override // defpackage.aci
    public void a(int i, int i2, int i3) {
        if (this.a != null) {
            this.a.recycle();
        }
        this.a = MotionEvent.obtain(0L, i3, 1, i * this.b.density, i2 * this.b.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i, long j) {
        this.d.a(i, j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i, String str) {
        this.d.a(i, str);
    }

    @Override // defpackage.aci
    public void a(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.a != null) {
                this.a.recycle();
            }
            this.a = MotionEvent.obtain(motionEvent);
        }
    }

    void a(String str, byte[] bArr) {
        if (str.length() > 32) {
            str = str.substring(0, 32);
        }
        new ajc(str.getBytes(HTTP.UTF_8)).a(bArr);
    }

    public abstract void b(Context context);

    public abstract void c(Context context);
}
