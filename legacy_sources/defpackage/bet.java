package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity;
import java.util.ArrayList;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bet extends ArrayAdapter<bpe> {
    final /* synthetic */ FantasyCompetitionsActivity a;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public bet(com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity r3, android.content.Context r4) {
        /*
            r2 = this;
            r2.a = r3
            int r0 = defpackage.bbr.settings_fantasy_list_item
            java.util.ArrayList r1 = com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity.a(r3)
            r2.<init>(r4, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bet.<init>(com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity, android.content.Context):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        View view3;
        View inflate;
        ArrayList arrayList;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.settings_fantasy_list_item, (ViewGroup) null);
                if (inflate == null) {
                    return null;
                }
                try {
                    bev bevVar = new bev();
                    bevVar.a = (TextView) inflate.findViewById(bbq.separator);
                    bevVar.b = (TextView) inflate.findViewById(bbq.title);
                    bevVar.c = (TextView) inflate.findViewById(bbq.subtitle);
                    bevVar.e = (TextView) inflate.findViewById(bbq.free);
                    bevVar.d = (CheckBox) inflate.findViewById(bbq.checkbox);
                    bevVar.d.setTag(bevVar);
                    inflate.setTag(bevVar);
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
        bev bevVar2 = (bev) inflate.getTag();
        bevVar2.a.setVisibility(8);
        arrayList = this.a.f;
        bpe bpeVar = (bpe) arrayList.get(i);
        bevVar2.b.setText(bpeVar.b());
        IService F = this.a.F();
        if (F != null && !F.m(bpeVar.a()).isEmpty()) {
            bevVar2.c.setText("Import teams/Adjust settings");
            bevVar2.e.setText("SUBSCRIBED");
        } else {
            bevVar2.c.setText(bpeVar.c());
            bevVar2.e.setText(bpeVar.d());
        }
        bevVar2.e.setVisibility(0);
        bevVar2.d.setVisibility(8);
        return inflate;
    }
}
