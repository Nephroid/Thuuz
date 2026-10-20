package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.nobar.MessageActivity;

/* loaded from: classes.dex */
public class bky implements View.OnClickListener {
    final /* synthetic */ MessageActivity a;

    public bky(MessageActivity messageActivity) {
        this.a = messageActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.finish();
    }
}
