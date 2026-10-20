package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.thuuz.gtv.settings.loc.SettingsLocationsActivity;
import java.util.ArrayList;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bxb extends ArrayAdapter<bpu> {
    final /* synthetic */ SettingsLocationsActivity a;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public bxb(com.thuuz.gtv.settings.loc.SettingsLocationsActivity r3, android.content.Context r4) {
        /*
            r2 = this;
            r2.a = r3
            int r0 = defpackage.bbr.settings_location_list_item
            java.util.ArrayList r1 = com.thuuz.gtv.settings.loc.SettingsLocationsActivity.a(r3)
            r2.<init>(r4, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bxb.<init>(com.thuuz.gtv.settings.loc.SettingsLocationsActivity, android.content.Context):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        ArrayList arrayList;
        bpu bpuVar;
        if (view == null) {
            try {
                view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.settings_location_list_item, (ViewGroup) null);
                if (view == null) {
                    return null;
                }
                bxa bxaVar = new bxa(null);
                bxaVar.a = (TextView) view.findViewById(bbq.title);
                bxaVar.b = (TextView) view.findViewById(bbq.subtitle);
                bxaVar.c = (ImageView) view.findViewById(bbq.checked_image);
                view.setTag(bxaVar);
            } catch (OutOfMemoryError e) {
                outOfMemoryError = e;
                view2 = view;
                System.gc();
                System.gc();
                this.a.a(outOfMemoryError);
                return view2;
            } catch (Throwable th2) {
                th = th2;
                view2 = view;
                this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
                return view2;
            }
        }
        View view3 = view;
        try {
            arrayList = this.a.g;
            bpuVar = (bpu) arrayList.get(i);
        } catch (OutOfMemoryError e2) {
            outOfMemoryError = e2;
            view2 = view3;
            System.gc();
            System.gc();
            this.a.a(outOfMemoryError);
            return view2;
        } catch (Throwable th3) {
            th = th3;
            view2 = view3;
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
            return view2;
        }
        if (bpuVar == null) {
            this.a.e().severe("cannot get location!");
            return view3;
        }
        if (this.a.F() != null) {
            bxa bxaVar2 = (bxa) view3.getTag();
            bxaVar2.a.setText(bpuVar.c());
            bxaVar2.b.setText(bpuVar.j());
            Integer num = 0;
            bpu a = this.a.j().ax().a();
            if (a != null) {
                num = a.a();
            }
            if (num.equals(bpuVar.a())) {
                bxaVar2.c.setVisibility(0);
            } else {
                bxaVar2.c.setVisibility(4);
            }
            view2 = view3;
            return view2;
        }
        return view3;
    }
}
