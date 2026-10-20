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
import com.thuuz.gtv.activity.nobar.OnboardPage7LeaguesActivity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class blq extends ArrayAdapter<bpr> {
    HashSet<Integer> a;
    final /* synthetic */ OnboardPage7LeaguesActivity b;
    private CompoundButton.OnCheckedChangeListener c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public blq(OnboardPage7LeaguesActivity onboardPage7LeaguesActivity, Context context, List<bpr> list) {
        super(context, bbr.onboarding_list_item, list);
        this.b = onboardPage7LeaguesActivity;
        this.a = new HashSet<>();
        this.c = new bls(this.b, null);
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
        Set set2;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.onboarding_list_item, (ViewGroup) null);
            } catch (OutOfMemoryError e) {
                outOfMemoryError = e;
                view3 = view;
                System.gc();
                System.gc();
                this.b.a(outOfMemoryError);
                return view3;
            } catch (Throwable th2) {
                th = th2;
                view2 = view;
                this.b.e().log(Level.SEVERE, "Cannot handle: ", th);
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
            list = this.b.g;
            bpr bprVar = (bpr) list.get(i);
            if (bprVar != null) {
                textView.setText(bprVar.f());
                z = this.a.contains(Integer.valueOf(bprVar.g()));
            } else {
                z = false;
            }
            set = this.b.h;
            int size = set.size();
            View findViewById = inflate.findViewById(bbq.separator);
            if (i == 0 && size > 0) {
                ((TextView) inflate.findViewById(bbq.separatorTitle)).setText("Based on your favorite teams:");
                findViewById.setVisibility(0);
            } else {
                findViewById.setVisibility(8);
            }
            if (i >= size) {
                set2 = this.b.i;
                if (set2.size() > 0) {
                    if (i == size) {
                        ((TextView) inflate.findViewById(bbq.separatorTitle)).setText("Your favorites from Facebook:");
                        findViewById.setVisibility(0);
                    } else {
                        findViewById.setVisibility(8);
                    }
                }
            }
            CheckBox checkBox = (CheckBox) inflate.findViewById(bbq.check_box);
            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(z);
            checkBox.setOnCheckedChangeListener(this.c);
            inflate.setOnClickListener(new blr(this, inflate));
            return inflate;
        } catch (OutOfMemoryError e2) {
            outOfMemoryError = e2;
            view3 = inflate;
            System.gc();
            System.gc();
            this.b.a(outOfMemoryError);
            return view3;
        } catch (Throwable th3) {
            th = th3;
            view2 = inflate;
            this.b.e().log(Level.SEVERE, "Cannot handle: ", th);
            return view2;
        }
    }

    public void a(Set<Integer> set) {
        this.a = new HashSet<>(set);
    }

    public Set<Integer> a() {
        return this.a;
    }
}
