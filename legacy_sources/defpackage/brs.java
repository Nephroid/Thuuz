package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.thuuz.gtv.settings.SettingsActivity;
import java.util.List;
import java.util.logging.Level;
import org.jraf.android.backport.switchwidget.Switch;

/* loaded from: classes.dex */
public class brs extends ArrayAdapter<brr> {
    final /* synthetic */ SettingsActivity a;
    private final brt b;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public brs(com.thuuz.gtv.settings.SettingsActivity r3, android.content.Context r4) {
        /*
            r2 = this;
            r2.a = r3
            int r0 = defpackage.bbr.settings_activity_list_item
            java.util.List r1 = com.thuuz.gtv.settings.SettingsActivity.b(r3)
            r2.<init>(r4, r0, r1)
            brt r0 = new brt
            r1 = 0
            r0.<init>(r2, r1)
            r2.b = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.brs.<init>(com.thuuz.gtv.settings.SettingsActivity, android.content.Context):void");
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
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.settings_activity_list_item, (ViewGroup) null);
            } catch (OutOfMemoryError e) {
                outOfMemoryError = e;
                view3 = view;
                System.gc();
                System.gc();
                this.a.a(outOfMemoryError);
                return view3;
            } catch (Throwable th2) {
                th = th2;
                view2 = view;
                this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
                return view2;
            }
            try {
                brw brwVar = new brw(null);
                brwVar.a = (TextView) inflate.findViewById(bbq.title);
                brwVar.b = (TextView) inflate.findViewById(bbq.subtitle);
                brwVar.c = (Switch) inflate.findViewById(bbq.checkbox);
                brwVar.e = (ImageView) inflate.findViewById(bbq.icon);
                brwVar.c.setTag(brwVar);
                brwVar.g = inflate.findViewById(bbq.rightside);
                inflate.setTag(brwVar);
            } catch (OutOfMemoryError e2) {
                outOfMemoryError = e2;
                view3 = inflate;
                System.gc();
                System.gc();
                this.a.a(outOfMemoryError);
                return view3;
            } catch (Throwable th3) {
                th = th3;
                view2 = inflate;
                this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
                return view2;
            }
        } else {
            inflate = view;
        }
        brw brwVar2 = (brw) inflate.getTag();
        inflate.setEnabled(true);
        list = this.a.h;
        brr brrVar = (brr) list.get(i);
        brwVar2.f = brrVar;
        brwVar2.a.setText(brrVar.b);
        brwVar2.b.setText(brrVar.c);
        if (brrVar.c == null || brrVar.c.length() == 0) {
            brwVar2.b.setVisibility(8);
        } else {
            brwVar2.b.setVisibility(0);
        }
        if (brrVar.a == SettingsActivity.SETTING_TYPE.SETTING_DISPLAY_SCORES) {
            brwVar2.g.setVisibility(0);
            brwVar2.c.setOnCheckedChangeListener(null);
            brwVar2.c.setChecked(this.a.j().aK());
            brwVar2.c.setOnCheckedChangeListener(this.b);
            brwVar2.d = true;
            if (this.a.j().e()) {
                inflate.setEnabled(true);
                brwVar2.c.setFocusable(false);
            } else {
                inflate.setEnabled(false);
            }
        } else {
            brwVar2.g.setVisibility(8);
            brwVar2.d = false;
        }
        brwVar2.e.setImageResource(brrVar.d);
        return inflate;
    }
}
