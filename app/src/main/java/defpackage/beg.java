package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.fantasy.FantasyActivity;

/* loaded from: classes.dex */
public class beg implements View.OnClickListener {
    final /* synthetic */ FantasyActivity a;

    public beg(FantasyActivity fantasyActivity) {
        this.a = fantasyActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.g();
    }
}
