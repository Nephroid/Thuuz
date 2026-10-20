package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity;
import com.thuuz.gtv.data.FantasySite;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bgj extends ArrayAdapter<bgo> {
    final /* synthetic */ SettingsFantasyTeamsActivity a;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public bgj(com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity r3, android.content.Context r4) {
        /*
            r2 = this;
            r2.a = r3
            int r0 = defpackage.bbr.fantasy_list_item
            java.util.List r1 = com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity.a(r3)
            r2.<init>(r4, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bgj.<init>(com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity, android.content.Context):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        View inflate;
        List list;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.fantasy_list_item, (ViewGroup) null);
            } catch (OutOfMemoryError e) {
                outOfMemoryError = e;
                view2 = view;
            } catch (Throwable th2) {
                th = th2;
                view2 = view;
            }
            try {
                a(inflate);
            } catch (OutOfMemoryError e2) {
                outOfMemoryError = e2;
                view2 = inflate;
                System.gc();
                System.gc();
                this.a.a(outOfMemoryError);
                return view2;
            } catch (Throwable th3) {
                th = th3;
                view2 = inflate;
                this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
                return view2;
            }
        } else {
            inflate = view;
        }
        if (inflate == null) {
            return null;
        }
        list = this.a.k;
        bgo bgoVar = (bgo) list.get(i);
        if (bgoVar != null) {
            bgp bgpVar = (bgp) inflate.getTag();
            IService F = this.a.F();
            if (F != null) {
                bpg bpgVar = bgoVar.b;
                bgpVar.d.setVisibility(a(bpgVar.a()) ? 0 : 8);
                bgpVar.b.setText(bpgVar.e());
                StringBuilder sb = new StringBuilder();
                if (bgoVar.a) {
                    bgpVar.a.setVisibility(0);
                    bpe j = F.j(bpgVar.b());
                    if (j != null) {
                        StringBuilder sb2 = new StringBuilder(j.b());
                        FantasySite b = F.b(bpgVar.c(), bpgVar.b());
                        if (b != null) {
                            sb2.append(", ");
                            sb2.append(b.c());
                        }
                        bgpVar.f.setText(sb2.toString());
                        bgpVar.f.setVisibility(0);
                    }
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) bgpVar.a.getLayoutParams();
                    if (marginLayoutParams != null) {
                        if (i == 0) {
                            marginLayoutParams.topMargin = 0;
                        } else {
                            marginLayoutParams.topMargin = this.a.getResources().getDimensionPixelSize(bbo.list_sep_top_margin);
                        }
                    }
                } else {
                    bgpVar.a.setVisibility(8);
                }
                if (bgoVar.a()) {
                    bgpVar.b.setGravity(5);
                } else {
                    bgpVar.b.setGravity(0);
                }
                sb.append(bpgVar.d());
                if (sb.length() > 0) {
                    bgpVar.c.setVisibility(0);
                    bgpVar.c.setText(sb.toString());
                } else {
                    bgpVar.c.setVisibility(8);
                }
                view2 = inflate;
                return view2;
            }
            return inflate;
        }
        return inflate;
    }

    private void a(View view) {
        bgl bglVar;
        bgm bgmVar;
        bgn bgnVar;
        if (view != null) {
            bgp bgpVar = new bgp(null);
            bgpVar.a = view.findViewById(bbq.separator);
            bgpVar.b = (TextView) view.findViewById(bbq.title);
            bgpVar.c = (TextView) view.findViewById(bbq.subtitle);
            bgpVar.d = view.findViewById(bbq.icon_container);
            bgpVar.e = (RelativeLayout) view.findViewById(bbq.selectable_item);
            bgpVar.e.setClickable(true);
            bgpVar.e.setFocusable(true);
            RelativeLayout relativeLayout = bgpVar.e;
            bglVar = this.a.n;
            relativeLayout.setOnClickListener(bglVar);
            RelativeLayout relativeLayout2 = bgpVar.e;
            bgmVar = this.a.o;
            relativeLayout2.setOnKeyListener(bgmVar);
            RelativeLayout relativeLayout3 = bgpVar.e;
            bgnVar = this.a.m;
            relativeLayout3.setOnLongClickListener(bgnVar);
            bgpVar.f = (TextView) view.findViewById(bbq.comp_title);
            view.findViewById(bbq.site_header).setVisibility(8);
            view.setTag(bgpVar);
        }
    }

    private boolean a(int i) {
        try {
            bph f = this.a.j().f(i);
            if (f != null) {
                return f.f();
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Problem: ", th);
        }
        return false;
    }
}
