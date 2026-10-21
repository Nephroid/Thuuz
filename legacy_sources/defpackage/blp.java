package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.activity.nobar.OnboardPage7LeaguesActivity;

/* loaded from: classes.dex */
public class blp implements AdapterView.OnItemClickListener {
    final /* synthetic */ OnboardPage7LeaguesActivity a;

    public blp(OnboardPage7LeaguesActivity onboardPage7LeaguesActivity) {
        this.a = onboardPage7LeaguesActivity;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.e(i);
    }
}
