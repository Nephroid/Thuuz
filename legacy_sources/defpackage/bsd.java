package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.thuuz.gtv.settings.SettingsAlertsActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bsd extends ArrayAdapter<SettingsAlertsActivity.ALERT_TYPE> {
    final /* synthetic */ SettingsAlertsActivity a;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public bsd(com.thuuz.gtv.settings.SettingsAlertsActivity r3, android.content.Context r4) {
        /*
            r2 = this;
            r2.a = r3
            int r0 = defpackage.bbr.settings_alert_list_item
            java.util.List r1 = com.thuuz.gtv.settings.SettingsAlertsActivity.b(r3)
            r2.<init>(r4, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bsd.<init>(com.thuuz.gtv.settings.SettingsAlertsActivity, android.content.Context):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        View view3;
        View inflate;
        List list;
        bse bseVar;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.settings_alert_list_item, (ViewGroup) null);
                if (inflate == null) {
                    return null;
                }
                try {
                    bsg bsgVar = new bsg(null);
                    bsgVar.a = (TextView) inflate.findViewById(bbq.title);
                    bsgVar.b = (TextView) inflate.findViewById(bbq.subtitle);
                    bsgVar.c = (CheckBox) inflate.findViewById(bbq.checkbox);
                    inflate.setTag(bsgVar);
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
        list = this.a.h;
        SettingsAlertsActivity.ALERT_TYPE alert_type = (SettingsAlertsActivity.ALERT_TYPE) list.get(i);
        bsg bsgVar2 = (bsg) inflate.getTag();
        bsgVar2.b.setVisibility(0);
        bsgVar2.c.setVisibility(8);
        bsgVar2.c.setOnCheckedChangeListener(null);
        boolean bD = this.a.j().bD();
        switch (alert_type) {
            case ALERT_INDEX_GLOBAL:
                bsgVar2.a.setText("Enable Alerts");
                bsgVar2.b.setText("");
                this.a.a(bsgVar2.c, bD);
                CheckBox checkBox = bsgVar2.c;
                bseVar = this.a.i;
                checkBox.setOnCheckedChangeListener(bseVar);
                bsgVar2.b.setVisibility(8);
                bsgVar2.c.setVisibility(0);
                break;
            case ALERT_INDEX_SPORT:
                bsgVar2.a.setText("Sport & Team Alerts");
                bsgVar2.b.setText("Know when a game just can't be missed; favorite teams");
                break;
            case ALERT_INDEX_FRIEND:
                bsgVar2.a.setText("Friend Alerts");
                bsgVar2.b.setText("Know when friends check in");
                break;
            case ALERT_INDEX_FANTASY:
                bsgVar2.a.setText("Fantasy Alerts");
                bsgVar2.b.setText("Know about injuries and hot players");
                break;
            case ALERT_INDEX_SOUNDS:
                String str = "Sound & Vibration";
                String str2 = "Customize how alerts sound";
                if (this.a.j().e()) {
                    str = "Alert Text Duration";
                    str2 = "Customize the duration of alert text";
                }
                bsgVar2.a.setText(str);
                bsgVar2.b.setText(str2);
                break;
            case ALERT_INDEX_REGISTRATION:
                bsgVar2.a.setText("Update Registration ID");
                bsgVar2.b.setText("Registration ID: " + this.a.j().aT());
                break;
        }
        if (alert_type == SettingsAlertsActivity.ALERT_TYPE.ALERT_INDEX_GLOBAL) {
            bsgVar2.a.setEnabled(true);
            bsgVar2.b.setEnabled(true);
        } else {
            bsgVar2.a.setEnabled(bD);
            bsgVar2.b.setEnabled(bD);
        }
        return inflate;
    }
}
