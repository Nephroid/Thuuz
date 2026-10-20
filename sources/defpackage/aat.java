package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;

/* loaded from: classes.dex */
public final class aat extends AsyncTask<Void, Void, Boolean> {
    final /* synthetic */ aas a;
    private final WebView b;
    private Bitmap c;

    public aat(aas aasVar, WebView webView) {
        this.a = aasVar;
        this.b = webView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public synchronized Boolean doInBackground(Void... voidArr) {
        boolean z;
        int width = this.c.getWidth();
        int height = this.c.getHeight();
        if (width == 0 || height == 0) {
            z = false;
        } else {
            int i = 0;
            for (int i2 = 0; i2 < width; i2 += 10) {
                for (int i3 = 0; i3 < height; i3 += 10) {
                    if (this.c.getPixel(i2, i3) != 0) {
                        i++;
                    }
                }
            }
            z = Boolean.valueOf(((double) i) / (((double) (width * height)) / 100.0d) > 0.1d);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Boolean bool) {
        ads adsVar;
        long j;
        long j2;
        Handler handler;
        long j3;
        aas.c(this.a);
        if (!bool.booleanValue() && !this.a.c()) {
            j = this.a.f;
            if (j > 0) {
                j2 = this.a.f;
                if (j2 > 0) {
                    if (adn.a(2)) {
                        adn.a("Ad not detected, scheduling another run.");
                    }
                    handler = this.a.d;
                    aas aasVar = this.a;
                    j3 = this.a.e;
                    handler.postDelayed(aasVar, j3);
                    return;
                }
                return;
            }
        }
        this.a.c = bool.booleanValue();
        adsVar = this.a.g;
        adsVar.a(this.a.a);
    }

    @Override // android.os.AsyncTask
    protected synchronized void onPreExecute() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        i = this.a.i;
        i2 = this.a.h;
        this.c = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        this.b.setVisibility(0);
        WebView webView = this.b;
        i3 = this.a.i;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, 0);
        i4 = this.a.h;
        webView.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(i4, 0));
        WebView webView2 = this.b;
        i5 = this.a.i;
        i6 = this.a.h;
        webView2.layout(0, 0, i5, i6);
        this.b.draw(new Canvas(this.c));
        this.b.invalidate();
    }
}
