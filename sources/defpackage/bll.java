package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.thuuz.gtv.activity.nobar.OnboardPage6TeamsActivity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bll extends ArrayAdapter<bpz> {
    final /* synthetic */ OnboardPage6TeamsActivity a;
    private Set<Integer> b;
    private CompoundButton.OnCheckedChangeListener c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bll(OnboardPage6TeamsActivity onboardPage6TeamsActivity, Context context, List<bpz> list) {
        super(context, bbr.onboarding_list_item, list);
        this.a = onboardPage6TeamsActivity;
        this.b = new HashSet();
        this.c = new bln(this.a, null);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    @SuppressLint({"InflateParams"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        View view3;
        View inflate;
        List list;
        boolean z;
        Set set;
        int i2;
        Set set2;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.onboarding_list_item, (ViewGroup) null);
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
            list = this.a.i;
            bpz bpzVar = (bpz) list.get(i);
            if (bpzVar != null) {
                textView.setText(bpzVar.c());
                z = this.b.contains(Integer.valueOf(bpzVar.b()));
            } else {
                z = false;
            }
            set = this.a.n;
            if (set != null) {
                set2 = this.a.n;
                i2 = set2.size();
            } else {
                i2 = 0;
            }
            View findViewById = inflate.findViewById(bbq.separator);
            if (i == 0 && i2 > 0) {
                ((TextView) inflate.findViewById(bbq.separatorTitle)).setText("Your Facebook favorites:");
                findViewById.setVisibility(0);
            } else {
                findViewById.setVisibility(8);
            }
            if (i >= i2) {
                if (i == i2) {
                    ((TextView) inflate.findViewById(bbq.separatorTitle)).setText("Your local teams:");
                    findViewById.setVisibility(0);
                } else {
                    findViewById.setVisibility(8);
                }
            }
            CheckBox checkBox = (CheckBox) inflate.findViewById(bbq.check_box);
            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(z);
            checkBox.setOnCheckedChangeListener(this.c);
            inflate.setOnClickListener(new blm(this, inflate));
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
