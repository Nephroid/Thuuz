package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.activity.nobar.OnboardPage6TeamsActivity;

/* loaded from: classes.dex */
public class bli implements AdapterView.OnItemClickListener {
    final /* synthetic */ OnboardPage6TeamsActivity a;

    public bli(OnboardPage6TeamsActivity onboardPage6TeamsActivity) {
        this.a = onboardPage6TeamsActivity;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.e(i);
    }
}
