package defpackage;

import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.activity.friends.ExpertsActivity;
import com.thuuz.gtv.settings.SettingsAccountActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bhz implements View.OnClickListener {
    final /* synthetic */ ExpertsActivity a;

    private bhz(ExpertsActivity expertsActivity) {
        this.a = expertsActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ListView listView;
        List list;
        try {
            listView = this.a.l;
            int positionForView = listView.getPositionForView(view);
            if (positionForView != -1) {
                list = this.a.f;
                if (list.size() <= positionForView) {
                    this.a.e().info("experts list manipulated just when clicked??");
                } else if (this.a.j().e()) {
                    if (!this.a.j().i()) {
                        this.a.a(SettingsAccountActivity.SOURCE.SOURCE_EXPERTS);
                    } else {
                        new bhy(this.a).onClick(view);
                    }
                } else {
                    bpc bpcVar = (bpc) view.getTag();
                    this.a.d(bpcVar.a());
                    this.a.j().a("go_to_user_profile", "experts", bpcVar.b(), 0L);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
