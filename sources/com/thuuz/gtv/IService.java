package com.thuuz.gtv;

import android.app.Activity;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.AlertSettings;
import com.thuuz.gtv.data.FantasySettingsCustomizations;
import com.thuuz.gtv.data.FantasySite;
import com.thuuz.gtv.data.FetchRequest;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.data.OnlineResourceCategory;
import defpackage.boz;
import defpackage.bpb;
import defpackage.bpe;
import defpackage.bpf;
import defpackage.bpg;
import defpackage.bpn;
import defpackage.bpo;
import defpackage.bpr;
import defpackage.bps;
import defpackage.bpx;
import defpackage.bpz;
import defpackage.cbd;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.json.JSONObject;
import twitter4j.auth.RequestToken;

/* loaded from: classes.dex */
public interface IService {
    void A();

    int B();

    boolean C();

    int a(String[] strArr);

    Activity a(Class cls, int i);

    bpn a(Date date, Date date2, String str, boolean z);

    bpo a(FetchRequest fetchRequest);

    bpr a(String str, String str2);

    bpz a(Integer num);

    String a(String str);

    Date a(JSONObject jSONObject);

    List<bpr> a(int i, boolean z);

    TreeSet<bpg> a(int i, int i2);

    void a(int i);

    void a(int i, int i2, String str);

    void a(int i, bpf bpfVar);

    void a(Activity activity);

    void a(bpz bpzVar);

    void a(Integer num, GameData gameData);

    void a(Integer num, Map<Integer, FantasySite> map);

    void a(Map<Integer, bpe> map);

    void a(Set<Integer> set, FetchRequest fetchRequest);

    void a(Set<Integer> set, Set<Integer> set2);

    void a(RequestToken requestToken);

    boolean a();

    boolean a(Class cls);

    bpx b(int i);

    AbstractActivity b(Class cls);

    FantasySite b(int i, int i2);

    GameData b(Integer num);

    String b(String str);

    List<bpx> b();

    void b(Integer num, Map<Integer, bpg> map);

    void b(Map<Integer, Integer> map);

    bpr c(int i);

    Map<Integer, List<bpr>> c();

    void c(int i, int i2);

    void c(String str);

    bps d(int i);

    List<bpr> d();

    List<bpr> d(String str);

    AlertSettings e(int i);

    List<bps> e();

    List<bpr> f();

    List<bpr> f(int i);

    List<bps> g(int i);

    boolean g();

    int h();

    void h(int i);

    void i(int i);

    boolean i();

    bpe j(int i);

    boolean j();

    Map<Integer, bpz> k();

    TreeSet<FantasySite> k(int i);

    bpg l(int i);

    void l();

    App m();

    Map<Integer, bpg> m(int i);

    bpf n(int i);

    Date n();

    FantasySettingsCustomizations o(int i);

    Map<Integer, Integer> o();

    boolean p();

    List<OnlineResourceCategory> q();

    TreeSet<bpe> r();

    RequestToken s();

    Map<Integer, List<boz>> t();

    void u();

    Map<String, String> v();

    cbd w();

    Map<Integer, bpb> x();

    List<bpr> y();

    void z();

    /* loaded from: classes.dex */
    public enum API_REQUEST_GAME_STATUS {
        LIVE(1),
        COMPLETED(2),
        UPCOMING(4),
        UPCOMING_AND_LIVE(5),
        ALL(7);

        private int status_int;

        API_REQUEST_GAME_STATUS(int i) {
            this.status_int = i;
        }

        public int a() {
            return this.status_int;
        }

        public static API_REQUEST_GAME_STATUS a(Integer num) {
            if (num.intValue() < 0) {
                return COMPLETED;
            }
            if (num.intValue() == 0) {
                return LIVE;
            }
            if (num.intValue() > 0) {
                return UPCOMING;
            }
            return ALL;
        }
    }
}
