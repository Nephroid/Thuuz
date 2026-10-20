package defpackage;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class cag extends AsyncTask<Void, Void, Bitmap> {
    WeakReference<caj> a;
    private String b;

    public cag(caj cajVar, String str) {
        this.a = new WeakReference<>(cajVar);
        this.b = str;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Bitmap doInBackground(Void... voidArr) {
        try {
            if (this.a.get() != null && this.b != null) {
                return bzz.a(this.b);
            }
            return null;
        } catch (Throwable th) {
            bzy.getStaticLogger(cag.class).log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Bitmap bitmap) {
        caj cajVar = this.a.get();
        if (cajVar != null && bitmap != null) {
            try {
                cajVar.a(bitmap);
            } catch (Throwable th) {
                bzy.getStaticLogger(cag.class).log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
    }
}
