package defpackage;

import android.content.Intent;
import android.view.View;
import com.thuuz.gtv.activity.fantasy.FantasyActivity;
import com.thuuz.gtv.activity.fantasy.FantasyLearnMoreActivity;

/* loaded from: classes.dex */
public class beh implements View.OnClickListener {
    final /* synthetic */ FantasyActivity a;

    public beh(FantasyActivity fantasyActivity) {
        this.a = fantasyActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.startActivity(new Intent(this.a.k(), (Class<?>) FantasyLearnMoreActivity.class));
    }
}
