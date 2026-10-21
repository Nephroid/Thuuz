package com.thuuz.gtv.data;

import com.thuuz.gtv.IService;
import defpackage.bbl;
import defpackage.bpk;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class FetchRequest {
    private final boolean a;
    private String b;
    private boolean c;
    private Date d;
    private Date e;
    private Integer f;
    private Integer g;
    private boolean h;
    private final Set<Integer> i;
    private WeakReference<bbl> j;
    private IService.API_REQUEST_GAME_STATUS k;
    private String l;
    private FORCING m;
    private Integer n;
    private int o;
    private REQ_TYPE p;
    private boolean q;
    private boolean r;

    public FetchRequest(Date date, Date date2, boolean z) {
        this.i = new HashSet();
        this.m = FORCING.RETURN_CACHED_THEN_FETCH;
        this.o = -1;
        this.p = REQ_TYPE.NORMAL;
        this.d = date;
        this.e = date2;
        this.a = z;
    }

    public FetchRequest(REQ_TYPE req_type, bbl bblVar, boolean z) {
        this.i = new HashSet();
        this.m = FORCING.RETURN_CACHED_THEN_FETCH;
        this.o = -1;
        this.p = REQ_TYPE.NORMAL;
        this.p = req_type;
        if (bblVar != null) {
            this.j = new WeakReference<>(bblVar);
        }
        this.a = z;
    }

    public boolean a(Date date) {
        switch (bpk.a[this.p.ordinal()]) {
            case 1:
                return true;
            case 2:
            case 3:
            case 4:
            case 5:
                return false;
            default:
                return j() != null && j().before(date) && i() != null && date.before(i());
        }
    }

    public boolean a() {
        return d() == IService.API_REQUEST_GAME_STATUS.ALL || d() == IService.API_REQUEST_GAME_STATUS.LIVE;
    }

    public boolean b() {
        return this.a;
    }

    public void a(int i) {
        this.o = i;
    }

    /* loaded from: classes.dex */
    public enum FORCING {
        FORCE,
        FORCE_NO_CACHE,
        RETURN_CACHED_THEN_FETCH,
        CACHED_ONLY,
        NOT_FORCED;

        public boolean a() {
            return this == FORCE || this == FORCE_NO_CACHE;
        }
    }

    public FetchRequest(IService.API_REQUEST_GAME_STATUS api_request_game_status, bbl bblVar, String str, FORCING forcing, Date date, Date date2, int i, int i2, boolean z) {
        this.i = new HashSet();
        this.m = FORCING.RETURN_CACHED_THEN_FETCH;
        this.o = -1;
        this.p = REQ_TYPE.NORMAL;
        if (bblVar != null) {
            this.j = new WeakReference<>(bblVar);
        }
        this.k = api_request_game_status;
        this.l = str;
        this.m = forcing;
        this.f = Integer.valueOf(i);
        this.g = Integer.valueOf(i2);
        this.d = date;
        this.e = date2;
        this.a = z;
    }

    public FetchRequest(Integer num, FORCING forcing, boolean z) {
        this.i = new HashSet();
        this.m = FORCING.RETURN_CACHED_THEN_FETCH;
        this.o = -1;
        this.p = REQ_TYPE.NORMAL;
        this.n = num;
        this.m = forcing;
        this.a = z;
    }

    public void a(bbl bblVar) {
        if (bblVar != null) {
            this.j = new WeakReference<>(bblVar);
        } else {
            this.j = null;
        }
    }

    public bbl c() {
        if (this.j == null) {
            return null;
        }
        return this.j.get();
    }

    public IService.API_REQUEST_GAME_STATUS d() {
        return this.k;
    }

    public String e() {
        return this.l;
    }

    public void a(String str) {
        this.l = str;
    }

    public FORCING f() {
        return this.m;
    }

    public Integer g() {
        return this.n;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean h() {
        return this.c;
    }

    public Date i() {
        return this.e;
    }

    public Date j() {
        return this.d;
    }

    public Integer k() {
        return this.f;
    }

    public Integer l() {
        return this.g;
    }

    public boolean m() {
        return this.h;
    }

    public void b(boolean z) {
        this.h = z;
    }

    public int n() {
        return this.o;
    }

    public Set<Integer> o() {
        return this.i;
    }

    public String p() {
        return this.p.a();
    }

    public REQ_TYPE q() {
        return this.p;
    }

    public void a(REQ_TYPE req_type) {
        this.p = req_type;
    }

    public boolean r() {
        return this.q;
    }

    public void c(boolean z) {
        this.q = z;
    }

    public boolean s() {
        return this.r;
    }

    /* loaded from: classes.dex */
    public enum REQ_TYPE {
        NORMAL,
        LIVE,
        SHARED,
        FEATURED,
        IDS,
        TEAM_IDS,
        FANTASY,
        RIGHTBAR,
        RIGHTBAR_FANTASY,
        WIDGET;

        public String a() {
            return WIDGET == this ? RIGHTBAR.name().toLowerCase() : name().toLowerCase();
        }
    }

    public String t() {
        return this.b;
    }
}
