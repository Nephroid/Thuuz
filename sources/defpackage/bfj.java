package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.thuuz.gtv.activity.fantasy.FantasySiteSelectActivity;
import com.thuuz.gtv.data.FantasySite;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bfj extends ArrayAdapter<FantasySite> {
    final /* synthetic */ FantasySiteSelectActivity a;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public bfj(com.thuuz.gtv.activity.fantasy.FantasySiteSelectActivity r3, android.content.Context r4) {
        /*
            r2 = this;
            r2.a = r3
            int r0 = defpackage.bbr.fant_site_select_list_item
            java.util.List r1 = com.thuuz.gtv.activity.fantasy.FantasySiteSelectActivity.a(r3)
            r2.<init>(r4, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bfj.<init>(com.thuuz.gtv.activity.fantasy.FantasySiteSelectActivity, android.content.Context):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        View view3;
        View inflate;
        List list;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.fant_site_select_list_item, (ViewGroup) null);
                if (inflate == null) {
                    return null;
                }
                try {
                    bfl bflVar = new bfl(null);
                    bflVar.a = (TextView) inflate.findViewById(bbq.separator);
                    bflVar.b = (TextView) inflate.findViewById(bbq.title);
                    bflVar.c = (TextView) inflate.findViewById(bbq.subtitle);
                    bflVar.e = (TextView) inflate.findViewById(bbq.free);
                    bflVar.d = (CheckBox) inflate.findViewById(bbq.checkbox);
                    bflVar.d.setTag(bflVar);
                    inflate.setTag(bflVar);
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
        list = this.a.f;
        FantasySite fantasySite = (FantasySite) list.get(i);
        bfl bflVar2 = (bfl) inflate.getTag();
        bflVar2.a.setVisibility(8);
        bflVar2.b.setText(fantasySite.c());
        String a = a(fantasySite);
        if (a == null || a.length() == 0) {
            bflVar2.c.setVisibility(8);
        } else {
            bflVar2.c.setVisibility(0);
            bflVar2.c.setText(a);
        }
        bflVar2.e.setVisibility(8);
        bflVar2.d.setVisibility(8);
        return inflate;
    }

    private String a(FantasySite fantasySite) {
        switch (fantasySite.d()) {
            case CONNECTED:
            case DISCONNECTED:
                return fantasySite.e();
            default:
                return "";
        }
    }
}
