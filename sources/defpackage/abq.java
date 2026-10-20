package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
class abq implements adj<Drawable> {
    final /* synthetic */ boolean a;
    final /* synthetic */ abo b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abq(abo aboVar, boolean z) {
        this.b = aboVar;
        this.a = z;
    }

    @Override // defpackage.adj
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Drawable b() {
        this.b.a(2, this.a);
        return null;
    }

    @Override // defpackage.adj
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Drawable b(InputStream inputStream) {
        byte[] bArr;
        try {
            bArr = aga.a(inputStream);
        } catch (IOException e) {
            bArr = null;
        }
        if (bArr == null) {
            this.b.a(2, this.a);
            return null;
        }
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (decodeByteArray != null) {
            return new BitmapDrawable(Resources.getSystem(), decodeByteArray);
        }
        this.b.a(2, this.a);
        return null;
    }
}
