package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.thuuz.gtv.settings.SettingsFriendAlertsActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class btf extends ArrayAdapter<SettingsFriendAlertsActivity.FRIEND_ALERT_INDEX> {
    final /* synthetic */ SettingsFriendAlertsActivity a;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public btf(com.thuuz.gtv.settings.SettingsFriendAlertsActivity r3, android.content.Context r4) {
        /*
            r2 = this;
            r2.a = r3
            int r0 = defpackage.bbr.settings_alert_list_item
            java.util.List r1 = com.thuuz.gtv.settings.SettingsFriendAlertsActivity.a(r3)
            r2.<init>(r4, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.btf.<init>(com.thuuz.gtv.settings.SettingsFriendAlertsActivity, android.content.Context):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        View view3;
        View inflate;
        btg btgVar;
        List list;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.settings_alert_list_item, (ViewGroup) null);
                if (inflate == null) {
                    return null;
                }
                try {
                    bti btiVar = new bti(null);
                    btiVar.a = (TextView) inflate.findViewById(bbq.title);
                    btiVar.b = (TextView) inflate.findViewById(bbq.subtitle);
                    btiVar.c = (CheckBox) inflate.findViewById(bbq.checkbox);
                    CheckBox checkBox = btiVar.c;
                    btgVar = this.a.h;
                    checkBox.setOnCheckedChangeListener(btgVar);
                    inflate.setTag(btiVar);
                } catch (OutOfMemoryError e) {
                    outOfMemoryError = e;
                    view3 = inflate;
                    System.gc();
                    System.gc();
                    this.a.a(outOfMemoryError);
                    return view3;
                } catch (Throwable th2) {
                    th = th2;
                    view2 = inflate;
                    this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
                    return view2;
                }
            } catch (OutOfMemoryError e2) {
                outOfMemoryError = e2;
                view3 = view;
            } catch (Throwable th3) {
                th = th3;
                view2 = view;
            }
        } else {
            inflate = view;
        }
        list = this.a.g;
        SettingsFriendAlertsActivity.FRIEND_ALERT_INDEX friend_alert_index = (SettingsFriendAlertsActivity.FRIEND_ALERT_INDEX) list.get(i);
        bti btiVar2 = (bti) inflate.getTag();
        switch (friend_alert_index) {
            case FRIEND_ALERT_INDEX_THUUZ_IT:
                btiVar2.a.setText("Check-in Alerts");
                btiVar2.b.setText("Get alerted when a friend checks into a game.");
                this.a.a(btiVar2.c, this.a.j().bB());
                break;
            case FRIEND_ALERT_INDEX_FRIEND_FOLLOWS:
                btiVar2.a.setText("Friend Follows");
                btiVar2.b.setText("Get alerted when someone follows you, or accepts your invitation to join Thuuz.");
                this.a.a(btiVar2.c, this.a.j().bC());
                break;
        }
        return inflate;
    }
}
