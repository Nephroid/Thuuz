package defpackage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.actionbarsherlock.widget.ActivityChooserView;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.util.ServerChoice;
import com.thuuz.gtv.widget.NetworkListenableImageView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class bms extends ArrayAdapter<Integer> {
    public static final Pattern a = Pattern.compile("#W\\d+#H\\d+");
    private final WeakReference<AbstractActivity> b;
    private final List<Integer> c;
    private final View.OnClickListener d;
    private final LruCache<String, Bitmap> e;
    private final cbb<Bitmap> f;
    private final ba g;
    private final ba h;

    public bms(AbstractActivity abstractActivity) {
        this(abstractActivity, new ArrayList());
    }

    public bms(AbstractActivity abstractActivity, List<Integer> list) {
        super(abstractActivity, bbr.game_layout, list);
        this.e = new LruCache<>(140);
        this.f = new cbb<>(300000L);
        this.b = new WeakReference<>(abstractActivity);
        this.c = list;
        this.d = a();
        this.g = new ba(abstractActivity.j().bW(), new cao(this.e));
        this.h = new ba(abstractActivity.j().bW(), new bmv(this, null));
    }

    protected View.OnClickListener a() {
        return new bmt(this);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        View view3;
        Integer a2;
        View view4;
        boolean z;
        AbstractActivity abstractActivity;
        ba baVar;
        GameData gameData;
        try {
            a2 = a(i);
            boolean z2 = false;
            if (GuideActivity.h.equals(a2)) {
                if (view == null || !GuideActivity.h.equals(view.getTag())) {
                    view = l();
                }
                z2 = true;
                view2 = view;
            } else {
                view2 = view;
            }
            try {
                if (GuideActivity.f.equals(a2)) {
                    if (view2 == null || !GuideActivity.f.equals(view2.getTag())) {
                        view2 = m();
                    }
                    view4 = view2;
                    z = true;
                } else {
                    boolean z3 = z2;
                    view4 = view2;
                    z = z3;
                }
                if (viewGroup != null && view4 != null) {
                    try {
                        view4.setOnFocusChangeListener(viewGroup.getOnFocusChangeListener());
                    } catch (OutOfMemoryError e) {
                        view3 = view4;
                        outOfMemoryError = e;
                        System.gc();
                        System.gc();
                        k().log(Level.SEVERE, "OoM in GameListAdaptor: ");
                        AbstractActivity abstractActivity2 = this.b.get();
                        if (abstractActivity2 != null) {
                            abstractActivity2.a(outOfMemoryError);
                        }
                        view2 = view3;
                        return view2;
                    } catch (Throwable th2) {
                        th = th2;
                        view2 = view4;
                        k().log(Level.SEVERE, "cannot handle pos: " + i, th);
                        return view2;
                    }
                }
            } catch (OutOfMemoryError e2) {
                outOfMemoryError = e2;
                view3 = view2;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (OutOfMemoryError e3) {
            outOfMemoryError = e3;
            view3 = view;
        } catch (Throwable th4) {
            th = th4;
            view2 = view;
        }
        if (!z && (abstractActivity = this.b.get()) != null) {
            if (view4 == null || GuideActivity.h.equals(view4.getTag()) || GuideActivity.f.equals(view4.getTag())) {
                view4 = abstractActivity.a(viewGroup);
                abstractActivity.a(view4, b());
                view4.setOnClickListener(this.d);
                if (viewGroup != null && view4 != null) {
                    view4.setOnFocusChangeListener(viewGroup.getOnFocusChangeListener());
                }
            }
            if (a2 != null) {
                IService F = abstractActivity.F();
                ba baVar2 = this.g;
                if (F == null) {
                    baVar = baVar2;
                    gameData = null;
                } else {
                    GameData b = F.b(a2);
                    if (b == null || !b.al()) {
                        baVar = baVar2;
                        gameData = b;
                    } else {
                        baVar = this.h;
                        gameData = b;
                    }
                }
                bmm bmmVar = (bmm) abstractActivity.a(view4, a2, baVar);
                if (gameData != null) {
                    a(abstractActivity, bmmVar, gameData);
                    a(a2, abstractActivity, bmmVar, gameData);
                    a(bmmVar, gameData);
                    if (viewGroup != null && view4 != null && abstractActivity.j().e()) {
                        if (i == c().size() - 1) {
                            view4.setNextFocusDownId(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                        } else {
                            view4.setNextFocusDownId(-1);
                        }
                    }
                    view2 = view4;
                    return view2;
                }
                return view4;
            }
            return view4;
        }
        return view4;
    }

    public void a(AbstractActivity abstractActivity, bmm bmmVar, GameData gameData) {
        Integer num;
        if (abstractActivity != null && bmmVar != null && gameData != null && abstractActivity.j().a((Activity) abstractActivity)) {
            if (gameData.c(abstractActivity.F())) {
                bmmVar.y.setVisibility(0);
                bmmVar.x.setVisibility(0);
                Integer t = gameData.t();
                Integer s = gameData.s();
                if (gameData.d(abstractActivity.F())) {
                    num = t;
                    t = s;
                } else {
                    num = Integer.valueOf(s.intValue());
                }
                a(t.intValue(), bmmVar.y, gameData.k(), gameData.l());
                a(num.intValue(), bmmVar.x, gameData.k(), gameData.l());
                return;
            }
            bmmVar.y.setVisibility(8);
            bmmVar.x.setVisibility(8);
        }
    }

    private void a(int i, NetworkListenableImageView networkListenableImageView, String str, String str2) {
        if (networkListenableImageView.getTag() != null && !((Integer) networkListenableImageView.getTag()).equals(Integer.valueOf(i))) {
            networkListenableImageView.setImageBitmap(null);
            networkListenableImageView.setTag(null);
        }
        networkListenableImageView.setImageUrl(ServerChoice.q + "static/img/teams/100/" + str + "/" + str2 + "/" + i + ".png", this.g);
    }

    private void a(Integer num, AbstractActivity abstractActivity, bmm bmmVar, GameData gameData) {
        try {
            Object tag = bmmVar.w.getTag();
            if (tag != null && (tag instanceof Integer) && !((Integer) tag).equals(num)) {
                bmmVar.w.setImageBitmap(null);
                bmmVar.w.setTag(null);
            }
            String o = gameData.o(abstractActivity.j().x());
            if (o != null && o.length() != 0) {
                switch (bmu.a[gameData.j().ordinal()]) {
                    case 1:
                        bmmVar.w.setImageUrl(o, this.h);
                        bmmVar.w.setVisibility(0);
                        bmmVar.w.setTag(num);
                        return;
                    case 2:
                        bmmVar.w.setImageUrl(o, this.g);
                        bmmVar.w.setTag(num);
                        bmmVar.w.setVisibility(0);
                        return;
                    default:
                        return;
                }
            }
        } catch (Throwable th) {
            k().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void a(bmm bmmVar, GameData gameData) {
        switch (bmu.a[gameData.j().ordinal()]) {
            case 2:
                if (bmmVar.h != null && bmmVar.h.isShown()) {
                    String url = bmmVar.h.getUrl();
                    Bitmap bitmap = null;
                    if (url != null && url.length() > 0) {
                        bitmap = this.e.get(url);
                    }
                    if (bitmap == null) {
                        bmmVar.h.setImageUrl(url, this.g);
                        return;
                    } else {
                        bmmVar.h.setImageBitmap(bitmap);
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    protected boolean b() {
        try {
            AbstractActivity abstractActivity = this.b.get();
            if (abstractActivity != null) {
                if (!abstractActivity.j().m() && !abstractActivity.j().ab() && !abstractActivity.j().ac()) {
                    if (!abstractActivity.j().J()) {
                        return false;
                    }
                }
                return true;
            }
        } catch (Throwable th) {
            k().log(Level.SEVERE, "problem: ", th);
        }
        return false;
    }

    private View l() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.dummy_progress_layout, (ViewGroup) null);
        if (inflate == null) {
            return null;
        }
        inflate.setTag(GuideActivity.h);
        return inflate;
    }

    private View m() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.dummy_no_games_layout, (ViewGroup) null);
        if (inflate == null) {
            return null;
        }
        inflate.setTag(GuideActivity.f);
        return inflate;
    }

    public Integer a(int i) {
        if (i < 0 || i >= this.c.size()) {
            return null;
        }
        return this.c.get(i);
    }

    public List<Integer> c() {
        return this.c;
    }

    public boolean d() {
        return this.c.size() == 1 && this.c.get(0).equals(GuideActivity.f);
    }

    public boolean e() {
        return this.c.size() == 1 && this.c.get(0).equals(GuideActivity.h);
    }

    public boolean f() {
        return d() || e();
    }

    @Override // android.widget.ArrayAdapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void remove(Integer num) {
        if (num != null) {
            try {
                super.remove(num);
                b(num);
            } catch (Throwable th) {
                k().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    private void b(Integer num) {
        IService F;
        GameData b;
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity != null && (F = abstractActivity.F()) != null && (b = F.b(num)) != null) {
            String o = b.o(abstractActivity.j().x());
            this.e.remove(o);
            this.f.b(o);
        }
    }

    public boolean a(ArrayList<Integer> arrayList) {
        boolean z = false;
        try {
            if (f() || (this.c.size() > 0 && arrayList.containsAll(this.c))) {
                z = true;
            }
            new ArrayList(this.c).retainAll(arrayList);
            ArrayList arrayList2 = new ArrayList(this.c);
            arrayList2.removeAll(arrayList);
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                b((Integer) it.next());
            }
            this.c.clear();
            this.c.addAll(arrayList);
            return z;
        } catch (Throwable th) {
            boolean z2 = z;
            k().log(Level.SEVERE, "problem: ", th);
            return z2;
        }
    }

    public void g() {
        this.c.clear();
        j();
    }

    public Map<String, Bitmap> h() {
        return this.e.snapshot();
    }

    public void i() {
        g();
    }

    public void j() {
        this.e.evictAll();
        this.f.a();
    }

    public Logger k() {
        Logger logger = Logger.getLogger(getClass().getSimpleName() + ":thuuz");
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    public void a(Map<String, Bitmap> map) {
        if (map != null) {
            for (String str : map.keySet()) {
                this.e.put(str, map.get(str));
            }
        }
    }
}
