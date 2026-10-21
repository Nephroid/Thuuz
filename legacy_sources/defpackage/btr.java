package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.thuuz.gtv.settings.SettingsLocalTeamsActivity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class btr extends ArrayAdapter<bpz> {
    final /* synthetic */ SettingsLocalTeamsActivity a;
    private Set<Integer> b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public btr(SettingsLocalTeamsActivity settingsLocalTeamsActivity, Context context, List<bpz> list) {
        super(context, bbr.settings_local_list_item, list);
        this.a = settingsLocalTeamsActivity;
        this.b = new HashSet();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        View view3;
        View inflate;
        List list;
        boolean z;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.settings_local_list_item, (ViewGroup) null);
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
        } else {
            inflate = view;
        }
        if (inflate == null) {
            return null;
        }
        try {
            TextView textView = (TextView) inflate.findViewById(bbq.title);
            list = this.a.g;
            bpz bpzVar = (bpz) list.get(i);
            if (bpzVar != null) {
                textView.setText(bpzVar.c());
                z = this.b.contains(Integer.valueOf(bpzVar.b()));
            } else {
                z = false;
            }
            View findViewById = inflate.findViewById(bbq.check_icon);
            View findViewById2 = inflate.findViewById(bbq.plus);
            if (z) {
                findViewById.setVisibility(0);
                findViewById2.setVisibility(8);
                textView.setTextColor(this.a.getResources().getColorStateList(bbn.button_blue_text_selector));
            } else {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
                textView.setTextColor(this.a.getResources().getColorStateList(bbn.button_text_selector));
            }
            return inflate;
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
    }

    public void a(Set<Integer> set) {
        this.b = new HashSet(set);
    }

    public Set<Integer> a() {
        return this.b;
    }
}
