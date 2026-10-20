package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.thuuz.gtv.settings.SettingsTeamSelectActivity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bvx extends ArrayAdapter<bwf> {
    final /* synthetic */ SettingsTeamSelectActivity a;
    private Set<Integer> b;
    private Set<Integer> c;
    private Set<Integer> d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bvx(SettingsTeamSelectActivity settingsTeamSelectActivity, Context context, List<bwf> list) {
        super(context, bbr.settings_team_list_item, list);
        this.a = settingsTeamSelectActivity;
        this.b = new HashSet();
        this.c = new HashSet();
        this.d = new HashSet();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        View view3;
        View inflate;
        View.OnClickListener onClickListener;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.settings_team_list_item, (ViewGroup) null);
                try {
                    a(inflate);
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
        if (inflate == null) {
            return null;
        }
        bvz bvzVar = (bvz) inflate.getTag();
        bwf item = getItem(i);
        bvzVar.a.setText(item.c());
        if (item.e() == null) {
            bvzVar.b.setVisibility(8);
        } else {
            bvzVar.b.setVisibility(0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) bvzVar.b.getLayoutParams();
            if (marginLayoutParams != null) {
                if (i == 0) {
                    marginLayoutParams.topMargin = 0;
                } else {
                    marginLayoutParams.topMargin = this.a.getResources().getDimensionPixelSize(bbo.list_sep_top_margin);
                }
            }
            bvzVar.c.setText(item.e());
            String f = item.f();
            boolean z = f != null && f.length() > 0;
            bvzVar.d.setText(f);
            bvzVar.d.setVisibility(z ? 0 : 8);
        }
        if ((item.a() && this.b.contains(Integer.valueOf(item.d()))) || (item.b() && this.d.contains(Integer.valueOf(item.d()))) || (item.g() && this.c.contains(Integer.valueOf(item.d())))) {
            bvzVar.e.setVisibility(0);
            bvzVar.f.setVisibility(8);
            bvzVar.a.setTextColor(this.a.getResources().getColor(bbn.button_blue_text_selector));
        } else {
            bvzVar.e.setVisibility(8);
            bvzVar.f.setVisibility(0);
            bvzVar.a.setTextColor(this.a.getResources().getColor(bbn.white_f1));
        }
        View view4 = bvzVar.g;
        onClickListener = this.a.m;
        view4.setOnClickListener(onClickListener);
        return inflate;
    }

    private void a(View view) {
        bvz bvzVar = new bvz(this.a, null);
        view.setTag(bvzVar);
        bvzVar.a = (TextView) view.findViewById(bbq.title);
        bvzVar.b = view.findViewById(bbq.separator);
        bvzVar.c = (TextView) view.findViewById(bbq.separatorTitle);
        bvzVar.d = (TextView) view.findViewById(bbq.separatorSubtitle);
        bvzVar.e = view.findViewById(bbq.check_icon);
        bvzVar.f = view.findViewById(bbq.plus);
        bvzVar.g = view.findViewById(bbq.selectable_item);
        bvzVar.g.setTag(view);
    }

    public void a(Set<Integer> set) {
        this.b = new HashSet(set);
    }

    public void b(Set<Integer> set) {
        this.c = new HashSet(set);
    }

    public void c(Set<Integer> set) {
        this.d = new HashSet(set);
    }

    public Set<Integer> a() {
        return this.b;
    }

    public Set<Integer> b() {
        return this.c;
    }

    public Set<Integer> c() {
        return this.d;
    }
}
