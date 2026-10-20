package defpackage;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import com.thuuz.gtv.activity.nobar.OnboardPage6TeamsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bln implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ OnboardPage6TeamsActivity a;

    private bln(OnboardPage6TeamsActivity onboardPage6TeamsActivity) {
        this.a = onboardPage6TeamsActivity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ bln(OnboardPage6TeamsActivity onboardPage6TeamsActivity, blh blhVar) {
        this(onboardPage6TeamsActivity);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        ListView listView;
        bll bllVar;
        try {
            View view = (View) compoundButton.getParent().getParent().getParent();
            listView = this.a.f;
            this.a.e(listView.getPositionForView(view));
            bllVar = this.a.g;
            bllVar.notifyDataSetChanged();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
