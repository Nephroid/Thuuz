package defpackage;

import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.thuuz.gtv.settings.SettingsFavSportsActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bsx extends ArrayAdapter<bsy> {
    final /* synthetic */ SettingsFavSportsActivity a;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public bsx(com.thuuz.gtv.settings.SettingsFavSportsActivity r3, android.content.Context r4) {
        /*
            r2 = this;
            r2.a = r3
            int r0 = defpackage.bbr.settings_fav_list_item
            java.util.List r1 = com.thuuz.gtv.settings.SettingsFavSportsActivity.b(r3)
            r2.<init>(r4, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bsx.<init>(com.thuuz.gtv.settings.SettingsFavSportsActivity, android.content.Context):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        View view3;
        View inflate;
        bsz bszVar;
        btb btbVar;
        bta btaVar;
        List list;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.settings_fav_list_item, (ViewGroup) null);
                if (inflate == null) {
                    return null;
                }
                try {
                    btd btdVar = new btd(null);
                    btdVar.a = (TextView) inflate.findViewById(bbq.separator);
                    btdVar.b = (TextView) inflate.findViewById(bbq.title);
                    btdVar.c = (TextView) inflate.findViewById(bbq.subtitle);
                    btdVar.d = (ImageView) inflate.findViewById(bbq.alert_icon);
                    btdVar.e = (RelativeLayout) inflate.findViewById(bbq.selectable_item);
                    RelativeLayout relativeLayout = btdVar.e;
                    bszVar = this.a.j;
                    relativeLayout.setOnClickListener(bszVar);
                    RelativeLayout relativeLayout2 = btdVar.e;
                    btbVar = this.a.l;
                    relativeLayout2.setOnLongClickListener(btbVar);
                    RelativeLayout relativeLayout3 = btdVar.e;
                    btaVar = this.a.k;
                    relativeLayout3.setOnKeyListener(btaVar);
                    inflate.setTag(btdVar);
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
                    this.a.e().log(Level.SEVERE, "Cannot create fav sports view: ", th);
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
                this.a.e().log(Level.SEVERE, "Cannot create fav sports view: ", th);
                return view2;
            }
        } else {
            inflate = view;
        }
        btd btdVar2 = (btd) inflate.getTag();
        list = this.a.h;
        bsy bsyVar = (bsy) list.get(i);
        if (bsyVar.a == null) {
            btdVar2.a.setVisibility(8);
        } else {
            btdVar2.a.setVisibility(0);
            btdVar2.a.setText(bsyVar.a);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) btdVar2.a.getLayoutParams();
            if (marginLayoutParams != null) {
                if (i == 0) {
                    marginLayoutParams.topMargin = 0;
                } else {
                    marginLayoutParams.topMargin = this.a.getResources().getDimensionPixelSize(bbo.list_sep_top_margin);
                }
            }
        }
        btdVar2.b.setText(bsyVar.b);
        btdVar2.c.setText(bsyVar.c);
        btdVar2.c.setVisibility((bsyVar.c == null || bsyVar.c.length() == 0) ? 8 : 0);
        btdVar2.d.setVisibility(bsyVar.d ? 0 : 8);
        btdVar2.d.setAlpha(!this.a.j().bD() ? 75 : MotionEventCompat.ACTION_MASK);
        return inflate;
    }
}
