package com.thuuz.gtv.data;

import defpackage.bpq;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FantasySite extends bpq implements Comparable<FantasySite> {

    /* loaded from: classes.dex */
    public enum FantasySiteStatus {
        NO_ACCOUNT(0),
        CONNECTED(1),
        DISCONNECTED(2),
        UNKNOWN(3);

        private int mStatus;

        FantasySiteStatus(int i) {
            this.mStatus = i;
        }

        public int a() {
            return this.mStatus;
        }
    }

    public FantasySite(JSONObject jSONObject) {
        super(jSONObject);
    }

    public int a() {
        return f("id");
    }

    public void a(int i) {
        a("id", i);
    }

    public int b() {
        return f("competition_id");
    }

    public void b(int i) {
        a("competition_id", i);
    }

    public String c() {
        return e("title");
    }

    public FantasySiteStatus d() {
        switch (f("status")) {
            case 0:
                return FantasySiteStatus.NO_ACCOUNT;
            case 1:
                return FantasySiteStatus.CONNECTED;
            case 2:
                return FantasySiteStatus.DISCONNECTED;
            default:
                return FantasySiteStatus.UNKNOWN;
        }
    }

    public void a(FantasySiteStatus fantasySiteStatus) {
        a("status", fantasySiteStatus.a());
    }

    public String e() {
        return e("status_message");
    }

    public void a(String str) {
        a("status_message", str);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(FantasySite fantasySite) {
        if (c() == null || fantasySite.c() == null) {
            return 0;
        }
        return c().toLowerCase().compareTo(fantasySite.c().toLowerCase());
    }

    public boolean f() {
        return d() == FantasySiteStatus.CONNECTED;
    }

    public boolean g() {
        return d() == FantasySiteStatus.DISCONNECTED;
    }

    public String h() {
        String c = c();
        if (c == null) {
            return null;
        }
        String lowerCase = c.toLowerCase();
        if (lowerCase.contains("yahoo")) {
            return "http://sports.yahoo.com/fantasy";
        }
        if (lowerCase.contains("nfl.com")) {
            return "http://www.nfl.com/fantasyfootball";
        }
        if (lowerCase.contains("cbs")) {
            return "http://www.cbssports.com/fantasy";
        }
        if (lowerCase.contains("flea")) {
            return "http://www.fleaflicker.com/";
        }
        if (lowerCase.contains("espn")) {
            return "http://games.espn.go.com/frontpage";
        }
        if (lowerCase.contains("myfant")) {
            return "http://myfantasyleague.com/";
        }
        return null;
    }

    public int i() {
        String c = c();
        if (c == null) {
            return 2;
        }
        String lowerCase = c.toLowerCase();
        if (lowerCase.contains("flea")) {
            return 1;
        }
        return !lowerCase.contains("espn") ? 0 : 2;
    }
}
