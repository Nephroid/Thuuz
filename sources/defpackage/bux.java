package defpackage;

import android.view.View;
import android.widget.ExpandableListView;
import com.thuuz.gtv.settings.SettingsSportsSelectActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bux implements ExpandableListView.OnChildClickListener {
    final /* synthetic */ SettingsSportsSelectActivity a;

    public bux(SettingsSportsSelectActivity settingsSportsSelectActivity) {
        this.a = settingsSportsSelectActivity;
    }

    @Override // android.widget.ExpandableListView.OnChildClickListener
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        buy buyVar;
        try {
            buyVar = this.a.h;
            this.a.a((buz) buyVar.getChild(i, i2));
            return true;
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
            return true;
        }
    }
}
