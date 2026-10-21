package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.android.volley.ParseError;
import com.android.volley.Request;

/* loaded from: classes.dex */
public class bi extends Request<Bitmap> {
    private static final Object e = new Object();
    private final aj<Bitmap> a;
    private final Bitmap.Config b;
    private final int c;
    private final int d;

    public bi(String str, aj<Bitmap> ajVar, int i, int i2, Bitmap.Config config, ai aiVar) {
        super(0, str, aiVar);
        a((al) new y(1000, 2, 2.0f));
        this.a = ajVar;
        this.b = config;
        this.c = i;
        this.d = i2;
    }

    @Override // com.android.volley.Request
    public Request.Priority r() {
        return Request.Priority.LOW;
    }

    private static int b(int i, int i2, int i3, int i4) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (i == 0) {
            return (int) ((i2 / i4) * i3);
        }
        if (i2 != 0) {
            double d = i4 / i3;
            if (i * d > i2) {
                return (int) (i2 / d);
            }
            return i;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public ah<Bitmap> a(ae aeVar) {
        ah<Bitmap> a;
        synchronized (e) {
            try {
                a = b(aeVar);
            } catch (OutOfMemoryError e2) {
                am.c("Caught OOM for %d byte image, url=%s", Integer.valueOf(aeVar.b.length), c());
                a = ah.a(new ParseError(e2));
            }
        }
        return a;
    }

    private ah<Bitmap> b(ae aeVar) {
        Bitmap bitmap;
        byte[] bArr = aeVar.b;
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (this.c == 0 && this.d == 0) {
            options.inPreferredConfig = this.b;
            bitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            int b = b(this.c, this.d, i, i2);
            int b2 = b(this.d, this.c, i2, i);
            options.inJustDecodeBounds = false;
            options.inSampleSize = a(i, i2, b, b2);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (decodeByteArray == null || (decodeByteArray.getWidth() <= b && decodeByteArray.getHeight() <= b2)) {
                bitmap = decodeByteArray;
            } else {
                bitmap = Bitmap.createScaledBitmap(decodeByteArray, b, b2, true);
                decodeByteArray.recycle();
            }
        }
        if (bitmap == null) {
            return ah.a(new ParseError(aeVar));
        }
        return ah.a(bitmap, aw.a(aeVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public void a(Bitmap bitmap) {
        this.a.a(bitmap);
    }

    static int a(int i, int i2, int i3, int i4) {
        float f = 1.0f;
        while (f * 2.0f <= Math.min(i / i3, i2 / i4)) {
            f *= 2.0f;
        }
        return (int) f;
    }
}
