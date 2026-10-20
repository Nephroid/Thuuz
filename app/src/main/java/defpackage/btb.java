package defpackage;

import android.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.thuuz.gtv.settings.SettingsFavSportsActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class btb implements View.OnLongClickListener {
    final /* synthetic */ SettingsFavSportsActivity a;

    private btb(SettingsFavSportsActivity settingsFavSportsActivity) {
        this.a = settingsFavSportsActivity;
    }

    public /* synthetic */ btb(SettingsFavSportsActivity settingsFavSportsActivity, bsv bsvVar) {
        this(settingsFavSportsActivity);
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        ListView listView;
        List list;
        List list2;
        try {
            listView = this.a.g;
            int positionForView = listView.getPositionForView(view);
            if (positionForView != -1 && positionForView >= 0) {
                list = this.a.h;
                if (positionForView <= list.size()) {
                    list2 = this.a.h;
                    bsy bsyVar = (bsy) list2.get(positionForView);
                    if (bsyVar == null) {
                        return false;
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(this.a.k());
                    builder.setTitle(bsyVar.b);
                    builder.setAdapter(new ArrayAdapter(this.a.k(), bbr.simple_list_item, bbq.text, new String[]{"Edit", "Delete"}), null);
                    AlertDialog create = builder.create();
                    create.getListView().setOnItemClickListener(new btc(this, view, positionForView, bsyVar, create));
                    this.a.a(create);
                    if (this.a.isDestroyed()) {
                        return false;
                    }
                    create.show();
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
            return false;
        }
    }
}
