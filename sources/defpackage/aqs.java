package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public class aqs implements aqt {
    private Context a;

    private aqs(Context context) {
        this.a = context;
    }

    @Override // defpackage.aqt
    public Drawable a(int i) {
        String str;
        switch (i) {
            case 0:
                str = "ic_plusone_small_off_client";
                break;
            case 1:
                str = "ic_plusone_medium_off_client";
                break;
            case 2:
                str = "ic_plusone_tall_off_client";
                break;
            default:
                str = "ic_plusone_standard_off_client";
                break;
        }
        return this.a.getResources().getDrawable(this.a.getResources().getIdentifier(str, "drawable", this.a.getPackageName()));
    }

    @Override // defpackage.aqt
    public boolean a() {
        return (this.a.getResources().getIdentifier("ic_plusone_small_off_client", "drawable", this.a.getPackageName()) == 0 || this.a.getResources().getIdentifier("ic_plusone_medium_off_client", "drawable", this.a.getPackageName()) == 0 || this.a.getResources().getIdentifier("ic_plusone_tall_off_client", "drawable", this.a.getPackageName()) == 0 || this.a.getResources().getIdentifier("ic_plusone_standard_off_client", "drawable", this.a.getPackageName()) == 0) ? false : true;
    }
}
