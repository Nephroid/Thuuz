package defpackage;

import android.app.Activity;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.GameData;
import com.thuuz.roboto.RobotoTextView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bmn extends bzy {
    private final GameData a;
    private WeakReference<AbstractActivity> b;
    private boolean c;
    private boolean d;
    private ba e;

    public bmn(GameData gameData, AbstractActivity abstractActivity) {
        this.a = gameData;
        if (abstractActivity != null) {
            this.b = new WeakReference<>(abstractActivity);
            this.c = abstractActivity.j().a((Activity) abstractActivity) && abstractActivity.j().cb();
        }
    }

    public bmh a(View view) {
        bmh bmhVar;
        Throwable th;
        OutOfMemoryError e;
        AbstractActivity a = a();
        try {
            bmhVar = (bmh) view.getTag();
        } catch (OutOfMemoryError e2) {
            bmhVar = null;
            e = e2;
        } catch (Throwable th2) {
            bmhVar = null;
            th = th2;
        }
        try {
            if (bmhVar == null) {
                getLogger().warning("renderGameView() - NULL ViewHolder.");
                bmhVar = null;
            } else {
                TreeSet<Integer> x = f().D().x();
                a(x, a, bmhVar);
                a(view, x);
            }
        } catch (OutOfMemoryError e3) {
            e = e3;
            System.gc();
            System.gc();
            if (a != null) {
                a.a(e);
            } else {
                f().D().bX();
            }
            return bmhVar;
        } catch (Throwable th3) {
            th = th3;
            getLogger().log(Level.SEVERE, "cannot render game: ", th);
            return bmhVar;
        }
        return bmhVar;
    }

    public void a(Set<Integer> set, AbstractActivity abstractActivity, bmh bmhVar) {
        bmhVar.a(Integer.valueOf(f().i()));
        ba j = j();
        if (j == null && abstractActivity != null) {
            j = abstractActivity.aE();
        }
        a(abstractActivity, bmhVar);
        a(abstractActivity, bmhVar, j);
        a(set, bmhVar);
        b(bmhVar);
        c(bmhVar);
        g(bmhVar);
        b(set, abstractActivity, bmhVar);
        boolean ak = f().ak();
        boolean h = h();
        if (h && !ak) {
            bmhVar.d(true);
            d(bmhVar);
        } else {
            bmhVar.d(false);
        }
        if (h || ak) {
            bmhVar.c(true);
            bmhVar.e(true);
            a(bmhVar, f().n(set));
            c(bmhVar, f().ai());
        } else {
            bmhVar.c(false);
            bmhVar.e(false);
        }
        a(bmhVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(bmh bmhVar, String str) {
        bmhVar.a(str);
    }

    private void b(Set<Integer> set, AbstractActivity abstractActivity, bmh bmhVar) {
        if ((abstractActivity != null ? f().D().a((Activity) abstractActivity) : false) || !f().a(set)) {
            bmhVar.g(false);
            bmhVar.h(false);
            return;
        }
        int j = f().j(set);
        if (j != 0) {
            if (b(set)) {
                bmhVar.g(true);
                bmhVar.c(j);
            } else {
                bmhVar.g(false);
            }
            if (a(set)) {
                bmhVar.h(true);
                bmhVar.d(j);
            } else {
                bmhVar.h(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(Set<Integer> set) {
        return f().c(set);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(Set<Integer> set) {
        return f().d(set);
    }

    private void a(Set<Integer> set, bmh bmhVar) {
        bmhVar.a(f(), set);
    }

    private void a(AbstractActivity abstractActivity, bmh bmhVar, ba baVar) {
        List<bpp> list;
        boolean z;
        boolean z2 = f().am() && f().D().a((Activity) abstractActivity);
        if (!z2) {
            list = null;
            z = z2;
        } else {
            List<bpp> an = f().an();
            z = an.size() > 0;
            list = an;
        }
        a(bmhVar, z);
        if (z && baVar != null) {
            Iterator<bpp> it = list.iterator();
            while (it.hasNext()) {
                String b = it.next().b();
                if (b != null && b.length() > 0) {
                    bmhVar.a(b, new bmo(this), baVar);
                    return;
                }
            }
        }
    }

    private void a(AbstractActivity abstractActivity, bmh bmhVar) {
        bmhVar.f(f().Y());
        int a = f().a(f().D());
        if (bmhVar.c != a) {
            bmhVar.g(a);
        }
        IService iService = null;
        if (abstractActivity != null) {
            iService = abstractActivity.F();
        }
        bmhVar.b(f().b(iService));
        b(bmhVar, f().ag());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(bmh bmhVar, String str) {
        bmhVar.c(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(bmh bmhVar, String str) {
        bmhVar.d(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractActivity a() {
        if (this.b == null) {
            return null;
        }
        return this.b.get();
    }

    protected void a(bmh bmhVar, boolean z) {
        int i = 8;
        if (this.c && z) {
            i = 0;
        }
        bmhVar.f(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, AbstractActivity abstractActivity) {
        List<bpp> an;
        if (abstractActivity != null) {
            try {
                IService F = abstractActivity.F();
                if (F != null && F.b(Integer.valueOf(i)) != null && (an = f().an()) != null && an.size() != 0) {
                    abstractActivity.a(i, (bpp) new ArrayList(an).get(0));
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    protected void a(View view, Set<Integer> set) {
        AbstractActivity a = a();
        boolean z = a != null && a.j().e();
        if (f().a(set)) {
            if (z) {
                view.setBackgroundResource(bbp.list_outside_focusrect_fav_selector);
                return;
            } else {
                view.setBackgroundResource(bbp.list_fav_selector);
                return;
            }
        }
        if (z) {
            view.setBackgroundResource(bbp.list_outside_focusrect_selector);
        } else {
            view.setBackgroundResource(bbp.list_selector);
        }
    }

    private void g(bmh bmhVar) {
        if (!f().al() || !f().k().equals("football")) {
            bmhVar.b(false);
            bmhVar.a(false);
            return;
        }
        boolean b = b();
        if (f().ae() == f().s().intValue()) {
            if (b) {
                if (f().af()) {
                    bmhVar.b(bbp.football_red_sm);
                } else {
                    bmhVar.b(bbp.football_white_sm);
                }
                bmhVar.b(true);
                bmhVar.a(false);
                return;
            }
            if (f().af()) {
                bmhVar.a(bbp.football_red_sm);
            } else {
                bmhVar.a(bbp.football_white_sm);
            }
            bmhVar.a(true);
            bmhVar.b(false);
            return;
        }
        if (f().ae() == f().t().intValue()) {
            if (b) {
                if (f().af()) {
                    bmhVar.a(bbp.football_red_sm);
                } else {
                    bmhVar.a(bbp.football_white_sm);
                }
                bmhVar.a(true);
                bmhVar.b(false);
                return;
            }
            if (f().af()) {
                bmhVar.b(bbp.football_red_sm);
            } else {
                bmhVar.b(bbp.football_white_sm);
            }
            bmhVar.b(true);
            bmhVar.a(false);
            return;
        }
        bmhVar.a(false);
        bmhVar.b(false);
    }

    protected boolean b() {
        return false;
    }

    protected void a(bmh bmhVar) {
        bmhVar.a(f());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, String str, Integer num, boolean z, bmh bmhVar) {
        try {
            TextView h = bmhVar.h(i);
            if (h != null && h.getClass() == RobotoTextView.class) {
                ((RobotoTextView) h).setTypeface(f().D(), z ? 4 : 6);
            }
            Resources resources = f().D().getResources();
            bmhVar.a(i, z ? resources.getColor(bbn.losing_team_text) : resources.getColor(bbn.white_ff));
            if (num == null || h == null) {
                bmhVar.a(i, str);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" ");
            int length = sb.toString().length();
            sb.append(num.toString());
            int length2 = num.toString().length() + length;
            SpannableString spannableString = new SpannableString(sb.toString());
            spannableString.setSpan(new ForegroundColorSpan(-7829368), length, length2, 0);
            spannableString.setSpan(new RelativeSizeSpan(0.8f), length, length2, 0);
            h.setText(spannableString, TextView.BufferType.SPANNABLE);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot render team name: ", th);
        }
    }

    protected void b(bmh bmhVar) {
        a(bmhVar.b(), f().w(), f().P(), c(), bmhVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean c() {
        if (!h()) {
            return false;
        }
        ArrayList<Integer> R = f().R();
        if (R.size() >= 2) {
            return R.get(0).intValue() < R.get(1).intValue();
        }
        return false;
    }

    protected void c(bmh bmhVar) {
        a(bmhVar.c(), f().x(), f().Q(), d(), bmhVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        if (!h()) {
            return false;
        }
        ArrayList<Integer> R = f().R();
        if (R.size() >= 2) {
            return R.get(0).intValue() > R.get(1).intValue();
        }
        return false;
    }

    protected void d(bmh bmhVar) {
        int i;
        int i2;
        int i3;
        int i4;
        try {
            e(bmhVar);
            f(bmhVar);
            bmhVar.i(true);
            GameData f = f();
            if (!f.S()) {
                bmhVar.a(bmhVar.d(), "");
                bmhVar.a(bmhVar.e(), "");
                return;
            }
            ArrayList<Integer> R = f.R();
            if (f.D().f(f.k())) {
                i = 0;
                i2 = 1;
            } else {
                i = 1;
                i2 = 0;
            }
            bmhVar.a(bmhVar.d(), a(i2));
            bmhVar.a(bmhVar.e(), a(i));
            Integer num = R.get(i2);
            Integer num2 = R.get(i);
            boolean z = num.intValue() < num2.intValue();
            Resources resources = f().D().getResources();
            int color = resources.getColor(bbn.white_ff);
            int color2 = resources.getColor(bbn.white_ff);
            if (z) {
                i4 = resources.getColor(bbn.losing_team_text);
                i3 = color2;
            } else if (num.intValue() > num2.intValue()) {
                i3 = resources.getColor(bbn.losing_team_text);
                i4 = color;
            } else {
                i3 = color2;
                i4 = color;
            }
            bmhVar.a(bmhVar.d(), i4);
            bmhVar.a(bmhVar.e(), i3);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot render scores: ", th);
        }
    }

    protected String a(int i) {
        return String.valueOf(f().R().get(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(bmh bmhVar) {
        bmhVar.f();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f(bmh bmhVar) {
        bmhVar.a(false, "");
        bmhVar.i(true);
        bmhVar.g();
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void a(bmm bmmVar, View view, GameData gameData) {
        if (view != null) {
            try {
                Resources resources = view.getResources();
                if (resources != null) {
                    view.setBackgroundColor(resources.getColor(bbn.game_cell_bg));
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "Cannot handle: ", th);
                return;
            }
        }
        if (bmmVar != null) {
            if (gameData != null) {
                switch (bmp.a[gameData.j().ordinal()]) {
                    case 1:
                        bmmVar.v.setText(gameData.B());
                        break;
                    case 2:
                        bmmVar.v.setText(gameData.a(GameData.DateRequestType.SHORT_DATETIME, false));
                        break;
                }
            }
            TextView h = bmmVar.h(bmmVar.b());
            if (h != null) {
                h.setSingleLine(false);
            }
            TextView h2 = bmmVar.h(bmmVar.c());
            if (h2 != null) {
                h2.setSingleLine(false);
            }
        }
    }

    public String e() {
        GameData f = f();
        return f.w() + " " + f.aq() + " " + f.x();
    }

    public GameData f() {
        return this.a;
    }

    public String g() {
        return f().w() + " " + this.a.aq() + " " + f().x();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean h() {
        if (i()) {
            return true;
        }
        return f().D().aK();
    }

    public void b(boolean z) {
        this.d = z;
    }

    public boolean i() {
        return this.d;
    }

    public ba j() {
        return this.e;
    }

    public void a(ba baVar) {
        this.e = baVar;
    }
}
