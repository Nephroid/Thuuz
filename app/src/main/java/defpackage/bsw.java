package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.settings.SettingsFavSportsActivity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bsw implements AdapterView.OnItemClickListener {
    final /* synthetic */ SettingsFavSportsActivity a;
    private int b;
    private final bsy c;

    public bsw(SettingsFavSportsActivity settingsFavSportsActivity, int i, bsy bsyVar) {
        this.a = settingsFavSportsActivity;
        this.b = i;
        this.c = bsyVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        List list;
        bsx bsxVar;
        bsx bsxVar2;
        bsx bsxVar3;
        bsx bsxVar4;
        bsx bsxVar5;
        try {
            boolean z = this.c.a != null;
            bsxVar = this.a.i;
            bsxVar.remove(this.c);
            int i2 = this.b;
            bsxVar2 = this.a.i;
            if (i2 >= bsxVar2.getCount()) {
                this.b--;
            }
            bsxVar3 = this.a.i;
            if (bsxVar3.getCount() > 0 && z && this.b >= 0) {
                bsxVar4 = this.a.i;
                bsy item = bsxVar4.getItem(this.b);
                if ((item.g && this.c.g) || ((item.h && this.c.h) || (item.f && this.c.f))) {
                    item.a = this.c.a;
                    bsxVar5 = this.a.i;
                    bsxVar5.notifyDataSetChanged();
                }
            }
        } catch (Throwable th) {
            Logger e = this.a.e();
            Level level = Level.SEVERE;
            StringBuilder append = new StringBuilder().append("problem getting fav sport pos: ").append(this.b).append(" after deleting former item from list of size: ");
            list = this.a.h;
            e.log(level, append.append(list.size()).toString(), th);
        }
    }
}
