package defpackage;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.TextView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.data.FetchRequest;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.view.HorizColumnScroll;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bnw extends AsyncTask<Void, Integer, Boolean> {
    final /* synthetic */ GuideActivity a;
    private final WeakReference<GuideActivity> b;
    private final boolean c;
    private boolean d;

    private bnw(GuideActivity guideActivity) {
        boolean aX;
        this.a = guideActivity;
        this.b = new WeakReference<>(this.a);
        aX = this.a.aX();
        this.c = aX;
    }

    public /* synthetic */ bnw(GuideActivity guideActivity, bnb bnbVar) {
        this(guideActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        if (this.b.get() != null) {
            try {
                this.a.e(true);
            } catch (Throwable th) {
                this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean doInBackground(Void... voidArr) {
        GuideActivity guideActivity;
        Date date;
        Date date2;
        boolean before;
        Object obj;
        Set i;
        HorizColumnScroll horizColumnScroll;
        HorizColumnScroll horizColumnScroll2;
        HorizColumnScroll horizColumnScroll3;
        HorizColumnScroll horizColumnScroll4;
        HorizColumnScroll horizColumnScroll5;
        try {
            guideActivity = this.b.get();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        if (guideActivity == null) {
            return null;
        }
        IService F = this.a.F();
        if (F == null) {
            return false;
        }
        F.h();
        date = this.a.u;
        if (date != null) {
            date2 = this.a.u;
            before = date2.before(new Date(System.currentTimeMillis() - 86400000));
        } else {
            this.d = true;
            before = false;
        }
        if (before) {
            this.a.e().info("super old data in Guide; clearing all before refresh");
            this.d = true;
        }
        bpn a = F.a(new Date(F.n().getTime() - 691200000), new Date(F.n().getTime() + 691200000), this.a.j().Z(), this.a.j().a((Activity) guideActivity));
        this.a.u = new Date();
        if (a == null) {
            return false;
        }
        HashSet hashSet = new HashSet();
        Iterator<Date> it = a.a.iterator();
        while (it.hasNext()) {
            int e = GameData.e(it.next());
            horizColumnScroll5 = this.a.l;
            if (horizColumnScroll5.b(Integer.valueOf(e)) == null && e != 0) {
                hashSet.add(Integer.valueOf(e));
            }
        }
        if (a.b) {
            horizColumnScroll4 = this.a.l;
            if (horizColumnScroll4.b((Integer) (-1)) == null) {
                hashSet.add(-1);
            }
        }
        if (a.d) {
            horizColumnScroll3 = this.a.l;
            if (horizColumnScroll3.b((Integer) 1) == null) {
                hashSet.add(1);
            }
        }
        if (a.c) {
            horizColumnScroll2 = this.a.l;
            if (horizColumnScroll2.b((Integer) 0) == null) {
                hashSet.add(0);
            }
        }
        Iterator it2 = new HashSet(hashSet).iterator();
        while (it2.hasNext()) {
            Integer num = (Integer) it2.next();
            horizColumnScroll = this.a.l;
            if (horizColumnScroll.b(num) != null) {
                hashSet.remove(num);
            }
        }
        ArrayList<Integer> arrayList = new ArrayList(hashSet);
        this.a.b((List<Integer>) arrayList);
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            i = this.a.i(((Integer) it3.next()).intValue());
            arrayList2.add(i);
        }
        this.a.e().info("dates adding: " + hashSet);
        obj = this.a.t;
        synchronized (obj) {
            int i2 = 0;
            for (Integer num2 : arrayList) {
                Set set = (Set) arrayList2.get(i2);
                Integer[] numArr = new Integer[set.size() + 1];
                numArr[0] = num2;
                Iterator it4 = set.iterator();
                int i3 = 1;
                while (it4.hasNext()) {
                    numArr[i3] = (Integer) it4.next();
                    i3++;
                }
                publishProgress(numArr);
                i2++;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onProgressUpdate(Integer... numArr) {
        if (this.b.get() != null) {
            try {
                if (this.d) {
                    this.a.bh();
                    this.d = false;
                }
                Integer num = numArr[0];
                HashSet hashSet = null;
                if (numArr.length > 1) {
                    hashSet = new HashSet(numArr.length - 1);
                    hashSet.addAll(Arrays.asList(numArr).subList(1, numArr.length));
                }
                this.a.a(num.intValue(), (Set<Integer>) hashSet);
                if (!this.c) {
                    this.a.k = false;
                    this.a.aY();
                }
            } catch (Throwable th) {
                this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Boolean bool) {
        HorizColumnScroll horizColumnScroll;
        boolean z;
        Handler handler;
        HorizColumnScroll horizColumnScroll2;
        boolean z2;
        Handler handler2;
        HorizColumnScroll horizColumnScroll3;
        boolean z3;
        Handler handler3;
        TextView textView;
        TextView textView2;
        boolean z4;
        try {
            if (this.b.get() == null) {
                return;
            }
            try {
                if (bool.booleanValue()) {
                    if (this.a.aF().c.length == 0) {
                        this.a.e().info("no game dates; showing none-found");
                        textView2 = this.a.A;
                        textView2.setVisibility(0);
                        if (this.a.aL()) {
                            z4 = this.a.K;
                            if (z4) {
                                this.a.bv();
                            } else {
                                this.a.bu();
                            }
                        }
                    } else {
                        textView = this.a.A;
                        textView.setVisibility(8);
                    }
                } else if (this.a.d()) {
                    this.a.c(bbt.unable_to_update, bbt.pls_try_refresh_later);
                }
                this.a.b(FetchRequest.FORCING.RETURN_CACHED_THEN_FETCH);
                try {
                    horizColumnScroll3 = this.a.l;
                    horizColumnScroll3.setVisibility(0);
                    z3 = this.a.k;
                    if (!z3 || !this.c) {
                        this.a.aY();
                        if (bool.booleanValue()) {
                            this.a.e().info("after gamedates, posting to re-initialize scroll after delay");
                            this.a.k = false;
                            handler3 = this.a.m;
                            handler3.postDelayed(new bnz(this.a, this.c), 50L);
                        }
                    }
                } catch (Throwable th) {
                    this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
                }
                this.a.e(false);
                this.a.G = null;
            } catch (Throwable th2) {
                this.a.e().log(Level.SEVERE, "Cannot handle: ", th2);
                try {
                    horizColumnScroll2 = this.a.l;
                    horizColumnScroll2.setVisibility(0);
                    z2 = this.a.k;
                    if (!z2 || !this.c) {
                        this.a.aY();
                        if (bool.booleanValue()) {
                            this.a.e().info("after gamedates, posting to re-initialize scroll after delay");
                            this.a.k = false;
                            handler2 = this.a.m;
                            handler2.postDelayed(new bnz(this.a, this.c), 50L);
                        }
                    }
                } catch (Throwable th3) {
                    this.a.e().log(Level.SEVERE, "Cannot handle: ", th3);
                }
                this.a.e(false);
                this.a.G = null;
            }
        } finally {
            try {
                horizColumnScroll = this.a.l;
                horizColumnScroll.setVisibility(0);
                z = this.a.k;
                if (!z || !this.c) {
                    this.a.aY();
                    if (bool.booleanValue()) {
                        this.a.e().info("after gamedates, posting to re-initialize scroll after delay");
                        this.a.k = false;
                        handler = this.a.m;
                        handler.postDelayed(new bnz(this.a, this.c), 50L);
                    }
                }
            } catch (Throwable th4) {
                this.a.e().log(Level.SEVERE, "Cannot handle: ", th4);
            }
            this.a.e(false);
            this.a.G = null;
        }
    }
}
