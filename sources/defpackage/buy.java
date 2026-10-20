package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.settings.SettingsSportsSelectActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class buy extends BaseExpandableListAdapter {
    final /* synthetic */ SettingsSportsSelectActivity a;
    private List<buz> b = new ArrayList();

    public buy(SettingsSportsSelectActivity settingsSportsSelectActivity) {
        this.a = settingsSportsSelectActivity;
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        bpx bpxVar;
        if (i2 < 0) {
            return null;
        }
        try {
            buz buzVar = (buz) getGroup(i);
            if (buzVar != null) {
                bpxVar = buzVar.c;
                if (bpxVar == null) {
                    return null;
                }
                List<bpr> a = a(bpxVar);
                if (i2 < a.size()) {
                    return new buz(this.a, a.get(i2), (buw) null);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    private List<bpr> a(bpx bpxVar) {
        IService F;
        return (bpxVar == null || !bpxVar.d() || (F = this.a.F()) == null) ? new ArrayList() : F.f(bpxVar.a());
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        bpx bpxVar;
        bpx bpxVar2;
        try {
            buz buzVar = (buz) getGroup(i);
            if (buzVar != null) {
                bpxVar = buzVar.c;
                if (bpxVar != null) {
                    bpxVar2 = buzVar.c;
                    return a(bpxVar2).size();
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
        return 0;
    }

    public View a() {
        View view;
        Throwable th;
        OutOfMemoryError e;
        LayoutInflater layoutInflater;
        try {
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(-1, -2);
            layoutInflater = this.a.g;
            view = layoutInflater.inflate(bbr.sport_select_list_item, (ViewGroup) null);
            if (view == null) {
                return null;
            }
            try {
                view.setLayoutParams(layoutParams);
                return view;
            } catch (OutOfMemoryError e2) {
                e = e2;
                System.gc();
                System.gc();
                this.a.a(e);
                return view;
            } catch (Throwable th2) {
                th = th2;
                this.a.e().log(Level.SEVERE, "problem inflating sports select cell: ", th);
                return view;
            }
        } catch (OutOfMemoryError e3) {
            view = null;
            e = e3;
        } catch (Throwable th3) {
            view = null;
            th = th3;
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        View a;
        bpr bprVar;
        if (view == null) {
            try {
                a = a();
            } catch (Throwable th2) {
                th = th2;
                view2 = view;
                this.a.e().log(Level.SEVERE, "problem: ", th);
                return view2;
            }
        } else {
            a = view;
        }
        try {
            TextView textView = (TextView) a.findViewById(bbq.title);
            buz buzVar = (buz) getChild(i, i2);
            if (buzVar != null) {
                bprVar = buzVar.b;
                textView.setText(bprVar.f());
            }
            textView.setPadding(45, 0, 0, 0);
            return a;
        } catch (Throwable th3) {
            th = th3;
            view2 = a;
            this.a.e().log(Level.SEVERE, "problem: ", th);
            return view2;
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        if (i < this.b.size()) {
            return this.b.get(i);
        }
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.b.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        View a;
        bpx bpxVar;
        bpr bprVar;
        bpx c;
        bpx bpxVar2;
        if (view == null) {
            try {
                a = a();
            } catch (Throwable th2) {
                th = th2;
                view2 = view;
                this.a.e().log(Level.SEVERE, "problem: ", th);
                return view2;
            }
        } else {
            a = view;
        }
        if (a == null) {
            return null;
        }
        try {
            TextView textView = (TextView) a.findViewById(bbq.title);
            buz buzVar = (buz) getGroup(i);
            if (buzVar == null) {
                return a;
            }
            String a2 = buzVar.a();
            ((TextView) a.findViewById(bbq.subtitle)).setVisibility(8);
            bpxVar = buzVar.c;
            if (bpxVar != null) {
                bpxVar2 = buzVar.c;
                c = bpxVar2;
            } else {
                bprVar = buzVar.b;
                c = bprVar.c();
            }
            ImageView imageView = (ImageView) a.findViewById(bbq.disclosure_image);
            if (c.d()) {
                imageView.setVisibility(0);
                imageView.setImageDrawable(this.a.getResources().getDrawable(z ? bbp.disclosure_down : bbp.disclosure));
            } else {
                imageView.setVisibility(4);
            }
            textView.setText(a2);
            textView.setCompoundDrawablesWithIntrinsicBounds(this.a.getResources().getIdentifier("list_" + c.b(), "drawable", this.a.getPackageName()), 0, 0, 0);
            textView.setCompoundDrawablePadding(20);
            return a;
        } catch (Throwable th3) {
            th = th3;
            view2 = a;
            this.a.e().log(Level.SEVERE, "problem: ", th);
            return view2;
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    public void a(List<buz> list) {
        this.b = list;
    }
}
