package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public class aqr implements aqt {
    private Context a;

    private aqr(Context context) {
        this.a = context;
    }

    @Override // defpackage.aqt
    public Drawable a(int i) {
        String str;
        try {
            Resources resources = this.a.createPackageContext("com.google.android.gms", 4).getResources();
            switch (i) {
                case 0:
                    str = "ic_plusone_small";
                    break;
                case 1:
                    str = "ic_plusone_medium";
                    break;
                case 2:
                    str = "ic_plusone_tall";
                    break;
                default:
                    str = "ic_plusone_standard";
                    break;
            }
            return resources.getDrawable(resources.getIdentifier(str, "drawable", "com.google.android.gms"));
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    @Override // defpackage.aqt
    public boolean a() {
        try {
            this.a.createPackageContext("com.google.android.gms", 4).getResources();
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
