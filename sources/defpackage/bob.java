package defpackage;

import android.view.View;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.view.HorizColumnScroll;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class bob {
    public int a;
    public int b;
    public int[] c;
    final /* synthetic */ GuideActivity d;

    private bob(GuideActivity guideActivity) {
        this.d = guideActivity;
        this.c = new int[0];
    }

    public /* synthetic */ bob(GuideActivity guideActivity, bnb bnbVar) {
        this(guideActivity);
    }

    public IService.API_REQUEST_GAME_STATUS a(int i) {
        if (i == 0) {
            return IService.API_REQUEST_GAME_STATUS.LIVE;
        }
        if (i < 0) {
            return IService.API_REQUEST_GAME_STATUS.COMPLETED;
        }
        return IService.API_REQUEST_GAME_STATUS.UPCOMING;
    }

    public boolean a() {
        for (int i : this.c) {
            if (Integer.valueOf(i).intValue() == 0) {
                return true;
            }
        }
        return false;
    }

    public int[] b() {
        return this.c;
    }

    public int c() {
        if (a()) {
            return 0;
        }
        if (this.c.length == 0) {
            this.d.e().severe("vis codes length 0");
            return 0;
        }
        int i = this.c[0];
        if (this.c.length > 2) {
            this.d.e().info("using first-col code when visual columns > 2");
            return this.c[1];
        }
        return i;
    }

    public void a(Integer[] numArr) {
        int i = 0;
        ArrayList arrayList = new ArrayList();
        for (Integer num : numArr) {
            if (num != null) {
                arrayList.add(num);
            }
        }
        this.c = new int[arrayList.size()];
        Iterator it = arrayList.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                this.c[i2] = ((Integer) it.next()).intValue();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public boolean d() {
        HorizColumnScroll horizColumnScroll;
        HorizColumnScroll horizColumnScroll2;
        for (int i : this.c) {
            horizColumnScroll2 = this.d.l;
            View b = horizColumnScroll2.b(Integer.valueOf(i));
            if (b == null) {
                this.d.e().warning("cannot find keeper column for code: " + i);
            } else {
                bms bmsVar = (bms) ((cbn) b.getTag()).e.getGameListAdapter();
                if (bmsVar.f() || bmsVar.c().size() == 0) {
                    return true;
                }
            }
        }
        horizColumnScroll = this.d.l;
        int e = horizColumnScroll.e();
        if (e == this.c.length) {
            return false;
        }
        this.d.e().info("expected " + e + " vis columns, found: " + this.c.length + " so signaling to refresh with gamedates wholesale");
        return true;
    }

    public boolean a(Integer num) {
        for (int i : this.c) {
            if (num.intValue() == i) {
                return true;
            }
        }
        return false;
    }
}
