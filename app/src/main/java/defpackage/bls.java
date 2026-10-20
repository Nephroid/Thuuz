package defpackage;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import com.thuuz.gtv.activity.nobar.OnboardPage7LeaguesActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bls implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ OnboardPage7LeaguesActivity a;

    private bls(OnboardPage7LeaguesActivity onboardPage7LeaguesActivity) {
        this.a = onboardPage7LeaguesActivity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ bls(OnboardPage7LeaguesActivity onboardPage7LeaguesActivity, blo bloVar) {
        this(onboardPage7LeaguesActivity);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        ListView listView;
        blq blqVar;
        try {
            View view = (View) compoundButton.getParent().getParent().getParent();
            listView = this.a.j;
            this.a.e(listView.getPositionForView(view));
            blqVar = this.a.f;
            blqVar.notifyDataSetChanged();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
