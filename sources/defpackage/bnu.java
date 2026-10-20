package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.view.HorizColumnScroll;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bnu extends AsyncTask<bpo, bpo, Void> {
    final ArrayList<Integer> a;
    final ArrayList<Integer> b;
    final ArrayList<Integer> c;
    protected final TreeMap<Integer, Set<Integer>> d;
    private bpo e;
    private Integer[] f;
    private final WeakReference<GuideActivity> g;
    private boolean h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ bnu(GuideActivity guideActivity, boolean z, bnb bnbVar) {
        this(guideActivity, z);
    }

    private bnu(GuideActivity guideActivity, boolean z) {
        this.a = new ArrayList<>();
        this.b = new ArrayList<>();
        this.c = new ArrayList<>();
        this.d = new TreeMap<>();
        this.g = new WeakReference<>(guideActivity);
        this.h = z;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        Integer[] bc;
        GuideActivity guideActivity = this.g.get();
        if (guideActivity != null) {
            try {
                bc = guideActivity.bc();
                this.f = bc;
            } catch (Throwable th) {
                guideActivity.e().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void doInBackground(bpo... bpoVarArr) {
        GuideActivity guideActivity = this.g.get();
        if (guideActivity != null && !guideActivity.isDestroyed()) {
            try {
                this.e = bpoVarArr[0];
                if (this.e != null && !this.e.b() && guideActivity.F() != null) {
                    a();
                }
            } catch (Throwable th) {
                guideActivity.e().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
        return null;
    }

    public void a() {
        GuideActivity guideActivity = this.g.get();
        if (guideActivity != null && !guideActivity.isDestroyed()) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(guideActivity.I());
            a(this.e.c(), gregorianCalendar);
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:55:0x02a1 A[Catch: Throwable -> 0x02bf, TRY_LEAVE, TryCatch #2 {Throwable -> 0x02bf, blocks: (B:43:0x026a, B:45:0x0271, B:47:0x0279, B:48:0x0286, B:50:0x028e, B:52:0x0296, B:55:0x02a1, B:57:0x032c, B:59:0x0332), top: B:42:0x026a }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x032c A[Catch: Throwable -> 0x02bf, TRY_ENTER, TryCatch #2 {Throwable -> 0x02bf, blocks: (B:43:0x026a, B:45:0x0271, B:47:0x0279, B:48:0x0286, B:50:0x028e, B:52:0x0296, B:55:0x02a1, B:57:0x032c, B:59:0x0332), top: B:42:0x026a }] */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onPostExecute(java.lang.Void r9) {
        /*
            Method dump skipped, instructions count: 871
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bnu.onPostExecute(java.lang.Void):void");
    }

    private void a(ArrayList<Integer> arrayList, Integer num, Integer num2) {
        Iterator it = new ArrayList(arrayList).iterator();
        while (it.hasNext()) {
            Integer num3 = (Integer) it.next();
            if (num3.intValue() < num.intValue() || num2.intValue() < num3.intValue()) {
                arrayList.remove(num3);
            }
        }
    }

    private Integer a(Calendar calendar, GameData gameData, Calendar calendar2) {
        Integer b = b(calendar, gameData, calendar2);
        if (b == null) {
            return null;
        }
        a(b).add(Integer.valueOf(gameData.i()));
        return b;
    }

    protected synchronized Set<Integer> a(Integer num) {
        Set<Integer> set;
        set = this.d.get(num);
        if (set == null) {
            set = new HashSet<>();
            this.d.put(num, set);
        }
        return set;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c4, code lost:
    
        if (r1 == false) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a(java.util.List<java.lang.Integer> r13, java.util.Calendar r14) {
        /*
            Method dump skipped, instructions count: 404
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bnu.a(java.util.List, java.util.Calendar):void");
    }

    private Integer a(Integer num, Integer num2) {
        a(num2).add(num);
        return num2;
    }

    protected boolean b() {
        Map bb;
        Object obj;
        HorizColumnScroll horizColumnScroll;
        List<Integer> b;
        HorizColumnScroll horizColumnScroll2;
        GuideActivity guideActivity = this.g.get();
        if (guideActivity == null || guideActivity.isDestroyed()) {
            return false;
        }
        try {
            bb = guideActivity.bb();
            Set keySet = bb.keySet();
            obj = guideActivity.t;
            synchronized (obj) {
                horizColumnScroll = guideActivity.l;
                int size = horizColumnScroll.getSize();
                for (int i = 0; i < size; i++) {
                    horizColumnScroll2 = guideActivity.l;
                    cbn d = horizColumnScroll2.d(i);
                    if (d != null) {
                        this.b.add(d.a);
                    }
                }
                b = guideActivity.b((Map<Integer, Set<Integer>>) this.d);
                for (Integer num : b) {
                    if (keySet.contains(num)) {
                        this.b.remove(num);
                        if (!this.c.contains(num)) {
                            this.c.add(num);
                        }
                    } else if (!this.a.contains(num)) {
                        this.a.add(num);
                    }
                }
            }
            return this.a.size() > 0 || this.b.size() > 0;
        } catch (Throwable th) {
            guideActivity.e().log(Level.SEVERE, "cannot calc changed columns: ", th);
            return false;
        }
    }

    private Integer b(Calendar calendar, GameData gameData, Calendar calendar2) {
        GuideActivity guideActivity = this.g.get();
        if (guideActivity == null || guideActivity.isDestroyed()) {
            return null;
        }
        long timeInMillis = calendar2.getTimeInMillis() - calendar.getTimeInMillis();
        if (timeInMillis == 0) {
            guideActivity.e().severe("unexpected 0 as delta millis; game: " + gameData.q() + " at " + calendar2 + " , fetchTime: " + calendar.getTime());
            return null;
        }
        int a = a(GameData.a(calendar, calendar2));
        int i = (int) (timeInMillis / 86400000);
        if (i == 0) {
            i = (int) (timeInMillis / Math.abs(timeInMillis));
            a = a(i);
        }
        if (gameData.ak() && i < 0) {
            guideActivity.e().warning("ignoring game " + gameData.i() + " that is both upcoming status and has game date " + calendar2.getTime() + " that precedes 'now' by a day " + gameData.u() + " at " + gameData.v());
            return null;
        }
        if (gameData.am() && i > 0) {
            guideActivity.e().warning("ignoring game " + gameData.i() + " that is both completed status and has game date " + calendar2.getTime() + " that comes after 'now' by a day " + gameData.u() + " at " + gameData.v());
            return null;
        }
        return Integer.valueOf(a);
    }

    private int a(int i) {
        if (i == 0) {
            return 0;
        }
        return (i / Math.abs(i)) + i;
    }
}
