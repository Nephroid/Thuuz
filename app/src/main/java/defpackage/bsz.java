package defpackage;

import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.settings.SettingsFavSportsActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bsz implements View.OnClickListener {
    final /* synthetic */ SettingsFavSportsActivity a;

    private bsz(SettingsFavSportsActivity settingsFavSportsActivity) {
        this.a = settingsFavSportsActivity;
    }

    public /* synthetic */ bsz(SettingsFavSportsActivity settingsFavSportsActivity, bsv bsvVar) {
        this(settingsFavSportsActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ListView listView;
        List list;
        try {
            listView = this.a.g;
            int positionForView = listView.getPositionForView(view);
            if (positionForView != -1 && positionForView >= 0) {
                list = this.a.h;
                if (positionForView <= list.size()) {
                    this.a.e(positionForView);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
