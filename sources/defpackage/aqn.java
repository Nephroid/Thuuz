package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.google.android.gms.plus.PlusOneButton;

/* loaded from: classes.dex */
public class aqn implements View.OnClickListener, aqo {
    final /* synthetic */ PlusOneButton a;
    private final aqo b;

    public aqn(PlusOneButton plusOneButton, aqo aqoVar) {
        this.a = plusOneButton;
        this.b = aqoVar;
    }

    @Override // defpackage.aqo
    public void a(Intent intent) {
        int i;
        Context context = this.a.getContext();
        if (!(context instanceof Activity) || intent == null) {
            return;
        }
        i = this.a.e;
        ((Activity) context).startActivityForResult(intent, i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        View view2;
        view2 = this.a.a;
        Intent intent = (Intent) view2.getTag();
        if (this.b != null) {
            this.b.a(intent);
        } else {
            a(intent);
        }
    }
}
