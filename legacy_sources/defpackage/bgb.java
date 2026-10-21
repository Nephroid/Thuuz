package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamDetailActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bgb extends ArrayAdapter<bga> {
    final /* synthetic */ SettingsFantasyTeamDetailActivity a;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public bgb(com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamDetailActivity r3, android.content.Context r4) {
        /*
            r2 = this;
            r2.a = r3
            int r0 = defpackage.bbr.settings_fantasy_list_item
            java.util.List r1 = com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamDetailActivity.a(r3)
            r2.<init>(r4, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bgb.<init>(com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamDetailActivity, android.content.Context):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        View view3;
        View inflate;
        bgd bgdVar;
        List list;
        bph bphVar;
        boolean b;
        bph bphVar2;
        bph bphVar3;
        bph bphVar4;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.settings_fantasy_list_item, (ViewGroup) null);
                if (inflate == null) {
                    return null;
                }
                try {
                    bge bgeVar = new bge(null);
                    bgeVar.a = (TextView) inflate.findViewById(bbq.separator);
                    bgeVar.b = (TextView) inflate.findViewById(bbq.title);
                    bgeVar.c = (TextView) inflate.findViewById(bbq.subtitle);
                    bgeVar.e = (TextView) inflate.findViewById(bbq.free);
                    bgeVar.d = (CheckBox) inflate.findViewById(bbq.checkbox);
                    CheckBox checkBox = bgeVar.d;
                    bgdVar = this.a.h;
                    checkBox.setOnCheckedChangeListener(bgdVar);
                    bgeVar.d.setTag(bgeVar);
                    inflate.setTag(bgeVar);
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
                System.gc();
                System.gc();
                this.a.a(outOfMemoryError);
                return view3;
            } catch (Throwable th3) {
                th = th3;
                view2 = view;
                this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
                return view2;
            }
        } else {
            inflate = view;
        }
        list = this.a.j;
        bga bgaVar = (bga) list.get(i);
        bge bgeVar2 = (bge) inflate.getTag();
        bgeVar2.a.setVisibility(8);
        bgeVar2.b.setText(bgaVar.b);
        bgeVar2.c.setText(bgaVar.c);
        bgeVar2.e.setVisibility(8);
        bgeVar2.d.setVisibility(0);
        switch (bgaVar.a) {
            case ALERT_TYPE_COMPETITION:
                bphVar4 = this.a.k;
                b = bphVar4.c();
                break;
            case ALERT_TYPE_SCORING_POSITION:
                bphVar3 = this.a.k;
                b = bphVar3.d();
                break;
            case ALERT_TYPE_HOT_PLAYER:
                bphVar2 = this.a.k;
                b = bphVar2.e();
                break;
            case ALERT_TYPE_INJURY:
                bphVar = this.a.k;
                b = bphVar.b();
                break;
            default:
                b = false;
                break;
        }
        this.a.a(bgeVar2.d, b);
        return inflate;
    }
}
