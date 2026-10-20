package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public abstract class is {
    final it a;
    protected int b;
    protected io c;
    protected int d;

    private Drawable a(Context context, aff affVar, int i) {
        Resources resources = context.getResources();
        if (this.d <= 0) {
            return resources.getDrawable(i);
        }
        afg afgVar = new afg(i, this.d);
        Drawable a = affVar.a((aff) afgVar);
        if (a != null) {
            return a;
        }
        Drawable drawable = resources.getDrawable(i);
        if ((this.d & 1) != 0) {
            drawable = a(resources, drawable);
        }
        affVar.b(afgVar, drawable);
        return drawable;
    }

    protected Drawable a(Resources resources, Drawable drawable) {
        return afd.a(resources, drawable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, aff affVar, boolean z) {
        Drawable a = this.b != 0 ? a(context, affVar, this.b) : null;
        if (this.c != null) {
            this.c.a(this.a.a, a, false);
        }
        a(a, z, false, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, Bitmap bitmap, boolean z) {
        ix.a(bitmap);
        if ((this.d & 1) != 0) {
            bitmap = afd.a(bitmap);
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        if (this.c != null) {
            this.c.a(this.a.a, bitmapDrawable, true);
        }
        a(bitmapDrawable, z, false, true);
    }

    protected abstract void a(Drawable drawable, boolean z, boolean z2, boolean z3);
}
