package defpackage;

import android.view.View;
import android.widget.ExpandableListView;
import com.thuuz.gtv.settings.SettingsSportsSelectActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class buw implements ExpandableListView.OnGroupClickListener {
    final /* synthetic */ SettingsSportsSelectActivity a;

    public buw(SettingsSportsSelectActivity settingsSportsSelectActivity) {
        this.a = settingsSportsSelectActivity;
    }

    @Override // android.widget.ExpandableListView.OnGroupClickListener
    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
        buy buyVar;
        buy buyVar2;
        bpx bpxVar;
        bpx bpxVar2;
        try {
            buyVar = this.a.h;
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
        if (buyVar.getChildrenCount(i) <= 0) {
            buyVar2 = this.a.h;
            buz buzVar = (buz) buyVar2.getGroup(i);
            if (buzVar != null) {
                bpxVar = SettingsSportsSelectActivity.f;
                bpxVar2 = buzVar.c;
                if (bpxVar == bpxVar2) {
                    this.a.aL();
                } else {
                    this.a.a(buzVar);
                }
            }
            return true;
        }
        return false;
    }
}
