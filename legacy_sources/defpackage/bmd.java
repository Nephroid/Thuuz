package defpackage;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.net.http.EventHandler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.widget.ActivityChooserView;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.sliding.SlidingMenuItemType;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bmd extends BaseExpandableListAdapter {
    private final View.OnClickListener a;
    private ColorStateList c;
    private final ExpandableListView d;
    private final WeakReference<AbstractActivity> e;
    private final List<bmc> b = new ArrayList();
    private String f = getClass().getSimpleName() + ":thuuz";

    public bmd(ExpandableListView expandableListView, AbstractActivity abstractActivity, View.OnClickListener onClickListener) {
        this.a = onClickListener;
        this.d = expandableListView;
        this.e = new WeakReference<>(abstractActivity);
    }

    public void a(List<bmc> list) {
        this.b.clear();
        this.b.addAll(list);
    }

    public void a(bmc bmcVar) {
        try {
            int indexOf = this.b.indexOf(bmcVar);
            if (this.d.isGroupExpanded(indexOf)) {
                this.d.collapseGroup(indexOf);
            } else {
                this.d.expandGroup(indexOf);
                if (this.d.getLastVisiblePosition() < indexOf + 1) {
                    this.d.setSelection(indexOf);
                }
            }
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
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

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00b7 -> B:13:0x0004). Please report as a decompilation issue!!! */
    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        AbstractActivity abstractActivity;
        bmc bmcVar;
        bmc bmcVar2;
        if (i2 >= 0 && (abstractActivity = this.e.get()) != null) {
            try {
                bmcVar2 = (bmc) getGroup(i);
            } catch (Throwable th) {
                b().log(Level.SEVERE, "problem: ", th);
            }
            if (bmcVar2 == null) {
                bmcVar = null;
            } else {
                IService F = abstractActivity.F();
                if (F == null) {
                    bmcVar = null;
                } else if (bmcVar2.e() == SlidingMenuItemType.SPORT) {
                    List<bpr> a = F.a(bmcVar2.d(), true);
                    if (i2 < a.size()) {
                        bmc bmcVar3 = new bmc(SlidingMenuItemType.LEAGUE, a.get(i2).g());
                        bmcVar3.a(false);
                        bmcVar = bmcVar3;
                    }
                    bmcVar = null;
                } else {
                    if (bmcVar2.e() == SlidingMenuItemType.LEAGUE) {
                        if (bmcVar2.d() == -8) {
                            List<bpr> list = F.c().get(ThuuzService.b);
                            if (i2 < list.size()) {
                                bmcVar = new bmc(SlidingMenuItemType.LEAGUE, list.get(i2).g());
                            }
                        } else if (bmcVar2.d() == -9) {
                            List<bpr> f = F.f();
                            if (i2 < f.size()) {
                                bmcVar = new bmc(SlidingMenuItemType.LEAGUE, f.get(i2).g());
                            }
                        }
                    }
                    bmcVar = null;
                }
            }
            return bmcVar;
        }
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0072 -> B:6:0x000c). Please report as a decompilation issue!!! */
    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        int i2;
        AbstractActivity abstractActivity;
        try {
            abstractActivity = this.e.get();
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
        if (abstractActivity == null) {
            i2 = 0;
        } else {
            bmc bmcVar = (bmc) getGroup(i);
            if (bmcVar == null) {
                i2 = 0;
            } else {
                IService F = abstractActivity.F();
                if (F == null) {
                    i2 = 0;
                } else if (bmcVar.e() == SlidingMenuItemType.SPORT) {
                    i2 = F.a(bmcVar.d(), true).size();
                } else {
                    if (bmcVar.e() == SlidingMenuItemType.LEAGUE) {
                        if (bmcVar.d() == -8) {
                            i2 = F.c().get(ThuuzService.b).size();
                        } else if (bmcVar.d() == -9) {
                            i2 = F.f().size();
                        }
                    }
                    i2 = 0;
                }
            }
        }
        return i2;
    }

    public View a() {
        View view;
        AbstractActivity abstractActivity;
        try {
            abstractActivity = this.e.get();
        } catch (OutOfMemoryError e) {
            System.gc();
            System.gc();
            AbstractActivity abstractActivity2 = this.e.get();
            if (abstractActivity2 != null) {
                abstractActivity2.a(e);
            }
            view = null;
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
            view = null;
        }
        if (abstractActivity == null) {
            return null;
        }
        view = ((LayoutInflater) abstractActivity.getSystemService("layout_inflater")).inflate(bbr.sliding_drawer_list_item, (ViewGroup) null);
        if (view == null) {
            return null;
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        bmg bmgVar;
        View view3;
        bmc bmcVar;
        IService F;
        try {
            if (view == null) {
                view3 = a();
                try {
                    bmgVar = a(view3);
                } catch (Throwable th2) {
                    th = th2;
                    view2 = view3;
                    b().log(Level.SEVERE, "problem: ", th);
                    return view2;
                }
            } else {
                bmgVar = (bmg) view.getTag();
                view3 = view;
            }
            bmcVar = (bmc) getChild(i, i2);
        } catch (Throwable th3) {
            th = th3;
            view2 = view;
        }
        if (bmcVar != null && bmcVar.e() == SlidingMenuItemType.LEAGUE) {
            bmgVar.e.setVisibility(4);
            AbstractActivity abstractActivity = this.e.get();
            if (abstractActivity != null && (F = abstractActivity.F()) != null) {
                bmgVar.b.setPadding(abstractActivity.j().q(42), 0, 0, 0);
                bpr c = F.c(bmcVar.d());
                if (c != null && bmcVar.c()) {
                    a(bmgVar, c.c(), c);
                } else {
                    bmgVar.c.setVisibility(8);
                }
                a(bmgVar.a, c);
                if (c != null) {
                    bmgVar.a.setText(c.h());
                }
                a(bmgVar, abstractActivity, bmcVar, F);
                bmgVar.f.setOnClickListener(this.a);
                bmgVar.f.setTag(bmcVar);
                if (view3 != null && abstractActivity.j().e()) {
                    if (i == getGroupCount() - 1 && z) {
                        view3.setNextFocusDownId(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                    } else {
                        view3.setNextFocusDownId(-1);
                    }
                }
                view2 = view3;
                return view2;
            }
            return view3;
        }
        return view3;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        bmg bmgVar;
        View view3;
        AbstractActivity abstractActivity;
        bmc bmcVar;
        bpr ar;
        try {
            if (view == null) {
                view3 = a();
                try {
                    bmgVar = a(view3);
                } catch (Throwable th2) {
                    th = th2;
                    view2 = view3;
                    b().log(Level.SEVERE, "problem: ", th);
                    return view2;
                }
            } else {
                bmgVar = (bmg) view.getTag();
                view3 = view;
            }
            bmgVar.e.setVisibility(4);
            abstractActivity = this.e.get();
        } catch (Throwable th3) {
            th = th3;
            view2 = view;
        }
        if (abstractActivity != null && (bmcVar = (bmc) getGroup(i)) != null) {
            bmgVar.c.setVisibility(8);
            bmgVar.b.setPadding(0, 0, 0, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) bmgVar.b.getLayoutParams();
            if (marginLayoutParams != null) {
                marginLayoutParams.leftMargin = 0;
            }
            if (this.c != null) {
                bmgVar.a.setTextColor(this.c);
            }
            IService F = abstractActivity.F();
            int q = abstractActivity.j().q(10);
            switch (bmcVar.e()) {
                case GUIDE:
                case PROFILE:
                    bmgVar.a.setText(bmcVar.e().b());
                    break;
                case SPORT:
                    if (F != null) {
                        bpx b = F.b(bmcVar.d());
                        bmgVar.a.setText(b.c());
                        a(bmgVar, b, (bpr) null, q);
                        a(z, bmgVar, abstractActivity);
                        break;
                    }
                    break;
                case LEAGUE:
                    switch (bmcVar.d()) {
                        case EventHandler.ERROR_REDIRECT_LOOP /* -9 */:
                            ar = abstractActivity.ar();
                            a(z, bmgVar, abstractActivity);
                            break;
                        case EventHandler.ERROR_TIMEOUT /* -8 */:
                            ar = abstractActivity.aq();
                            a(z, bmgVar, abstractActivity);
                            break;
                        case EventHandler.ERROR_IO /* -7 */:
                            ar = abstractActivity.an();
                            break;
                        case EventHandler.ERROR_CONNECT /* -6 */:
                        case -4:
                        case -3:
                        default:
                            if (F == null) {
                                ar = null;
                                break;
                            } else {
                                ar = F.c(bmcVar.d());
                                break;
                            }
                        case EventHandler.ERROR_PROXYAUTH /* -5 */:
                            ar = abstractActivity.ap();
                            break;
                        case -2:
                            ar = abstractActivity.ao();
                            break;
                        case -1:
                            ar = abstractActivity.am();
                            break;
                    }
                    a(bmgVar.a, ar);
                    if (bmcVar.c()) {
                        if (ar != null) {
                            a(bmgVar, ar.c(), ar, q);
                            break;
                        }
                    } else {
                        bmgVar.c.setVisibility(8);
                        break;
                    }
                    break;
            }
            a(bmgVar, abstractActivity, bmcVar, F);
            bmgVar.f.setOnClickListener(this.a);
            bmgVar.f.setTag(bmcVar);
            if (view3 != null && abstractActivity.j().e()) {
                if (i == getGroupCount() - 1 && !z) {
                    view3.setNextFocusDownId(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                } else {
                    view3.setNextFocusDownId(-1);
                }
            }
            view2 = view3;
            return view2;
        }
        return view3;
    }

    private void a(bmg bmgVar, AbstractActivity abstractActivity, bmc bmcVar, IService iService) {
        Integer num = 8;
        int a = abstractActivity.a(bmcVar, iService);
        if (a > 0) {
            num = 0;
            bmgVar.d.setText(String.valueOf(a));
        }
        bmgVar.d.setVisibility(num.intValue());
    }

    private void a(boolean z, bmg bmgVar, AbstractActivity abstractActivity) {
        bmgVar.e.setImageDrawable(abstractActivity.getResources().getDrawable(z ? bbp.disclosure_down : bbp.disclosure));
        bmgVar.e.setVisibility(0);
    }

    private bmg a(View view) {
        bmg bmgVar = new bmg(this, null);
        bmgVar.a = (TextView) view.findViewById(bbq.title);
        bmgVar.b = view.findViewById(bbq.icon_container);
        bmgVar.c = (ImageView) view.findViewById(bbq.icon);
        bmgVar.d = (TextView) view.findViewById(bbq.live_count);
        bmgVar.e = (ImageView) view.findViewById(bbq.disclosure_image);
        bmgVar.f = view.findViewById(bbq.title_layout);
        view.setTag(bmgVar);
        return bmgVar;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    private void a(TextView textView, bpr bprVar) {
        ColorStateList textColors;
        if (bprVar != null) {
            try {
                AbstractActivity abstractActivity = this.e.get();
                if (abstractActivity != null) {
                    textView.setText(bprVar.f());
                    if (bprVar.a(abstractActivity.I())) {
                        if (this.c != null) {
                            textView.setTextColor(this.c);
                        }
                    } else {
                        ColorStateList colorStateList = abstractActivity.getResources().getColorStateList(bbn.gray_text_selector);
                        if (this.c == null && (textColors = textView.getTextColors()) != null && !textColors.equals(colorStateList)) {
                            this.c = textColors;
                        }
                        textView.setTextColor(colorStateList);
                    }
                }
            } catch (Throwable th) {
                b().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    private void a(bmg bmgVar, bpx bpxVar, bpr bprVar) {
        a(bmgVar, bpxVar, bprVar, 0);
    }

    private void a(bmg bmgVar, bpx bpxVar, bpr bprVar, int i) {
        if (bmgVar != null) {
            if (bpxVar == null) {
                bmgVar.c.setVisibility(8);
                return;
            }
            AbstractActivity abstractActivity = this.e.get();
            if (abstractActivity != null) {
                try {
                    String str = "list_" + bpxVar.b();
                    if (bprVar != null && !bprVar.a(abstractActivity.I())) {
                        str = str + "_out";
                    }
                    bmgVar.c.setImageDrawable(abstractActivity.getResources().getDrawable(abstractActivity.getResources().getIdentifier(str, "drawable", abstractActivity.getPackageName())));
                    bmgVar.c.setVisibility(0);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) bmgVar.b.getLayoutParams();
                    if (marginLayoutParams != null) {
                        marginLayoutParams.leftMargin = i;
                    }
                } catch (Throwable th) {
                    b().log(Level.SEVERE, "problem showing sport icon: ", th);
                }
            }
        }
    }

    public Logger b() {
        Logger logger = Logger.getLogger(this.f);
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    public synchronized void c() {
        e();
        notifyDataSetChanged();
    }

    public synchronized void d() {
        notifyDataSetChanged();
    }

    public synchronized void e() {
        IService F;
        AbstractActivity abstractActivity = this.e.get();
        if (abstractActivity != null && (F = abstractActivity.F()) != null) {
            try {
                List<bmc> arrayList = new ArrayList<>();
                boolean a = abstractActivity.j().a((Activity) abstractActivity);
                if (!a) {
                    b(arrayList);
                }
                arrayList.add(new bmc(SlidingMenuItemType.GUIDE, 0));
                arrayList.add(new bmc(SlidingMenuItemType.LEAGUE, -7));
                arrayList.add(new bmc(SlidingMenuItemType.LEAGUE, -1));
                arrayList.add(new bmc(SlidingMenuItemType.LEAGUE, -2));
                if (abstractActivity.j().i()) {
                    arrayList.add(new bmc(SlidingMenuItemType.LEAGUE, -5));
                }
                Set<Integer> o = abstractActivity.j().o();
                Iterator<bps> it = F.e().iterator();
                while (it.hasNext()) {
                    o.add(Integer.valueOf(it.next().d().g()));
                }
                Map<Integer, List<bpr>> c = F.c();
                List<bpr> list = c.get(ThuuzService.a);
                List<bpr> list2 = c.get(ThuuzService.b);
                HashSet<Integer> hashSet = new HashSet(o);
                for (bpr bprVar : list2) {
                    if (hashSet.contains(Integer.valueOf(bprVar.g()))) {
                        hashSet.remove(Integer.valueOf(bprVar.g()));
                    }
                }
                HashSet hashSet2 = new HashSet();
                Iterator<bpr> it2 = list.iterator();
                while (it2.hasNext()) {
                    hashSet2.add(Integer.valueOf(it2.next().g()));
                }
                hashSet.retainAll(hashSet2);
                if (hashSet.size() == 0) {
                    HashSet hashSet3 = new HashSet();
                    for (bpr bprVar2 : list) {
                        bpx c2 = bprVar2.c();
                        if (c2.d()) {
                            if (!hashSet3.contains(c2)) {
                                arrayList.add(new bmc(SlidingMenuItemType.SPORT, c2.a()));
                                hashSet3.add(c2);
                            }
                        } else {
                            arrayList.add(new bmc(SlidingMenuItemType.LEAGUE, bprVar2.g()));
                        }
                    }
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    for (Integer num : hashSet) {
                        if (F.c(num.intValue()) != null) {
                            arrayList2.add(new bmc(SlidingMenuItemType.LEAGUE, num.intValue()));
                        }
                    }
                    Collections.sort(arrayList2, new bmf(this));
                    arrayList.addAll(arrayList2);
                    if (F.f().size() > 0) {
                        arrayList.add(new bmc(SlidingMenuItemType.LEAGUE, -9));
                    }
                }
                if (list2.size() > 0) {
                    arrayList.add(new bmc(SlidingMenuItemType.LEAGUE, -8));
                }
                if (a) {
                    b(arrayList);
                }
                a(arrayList);
            } catch (Throwable th) {
                b().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    private void b(List<bmc> list) {
        list.add(new bmc(SlidingMenuItemType.PROFILE, 0));
    }
}
