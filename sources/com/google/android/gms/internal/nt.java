package com.google.android.gms.internal;

import android.net.http.Headers;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.internal.ji;
import com.samsung.android.sdk.richnotification.Utilities;
import defpackage.ain;
import defpackage.ara;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public final class nt extends jj implements ara {
    public static final ain CREATOR = new ain();
    private static final HashMap<String, ji.a<?, ?>> ae = new HashMap<>();
    public String A;
    public String B;
    public String C;
    public nt D;
    public String E;
    public String F;
    public String G;
    public String H;
    public nt I;
    public double J;
    public nt K;
    public double L;
    public String M;
    public nt N;
    public List<nt> O;
    public String P;
    public String Q;
    public String R;
    public String S;
    public nt T;
    public String U;
    public String V;
    public String W;
    public nt X;
    public String Y;
    public String Z;
    public final Set<Integer> a;
    public String aa;
    public String ab;
    public String ac;
    public String ad;
    public final int b;
    public nt c;
    public List<String> d;
    public nt e;
    public String f;
    public String g;
    public String h;
    public List<nt> i;
    public int j;
    public List<nt> k;
    public nt l;
    public List<nt> m;
    public String n;
    public String o;
    public nt p;
    public String q;
    public String r;
    public String s;
    public List<nt> t;
    public String u;
    public String v;
    public String w;
    public String x;
    public String y;
    public String z;

    static {
        ae.put("about", ji.a.a("about", 2, nt.class));
        ae.put("additionalName", ji.a.e("additionalName", 3));
        ae.put("address", ji.a.a("address", 4, nt.class));
        ae.put("addressCountry", ji.a.d("addressCountry", 5));
        ae.put("addressLocality", ji.a.d("addressLocality", 6));
        ae.put("addressRegion", ji.a.d("addressRegion", 7));
        ae.put("associated_media", ji.a.b("associated_media", 8, nt.class));
        ae.put("attendeeCount", ji.a.a("attendeeCount", 9));
        ae.put("attendees", ji.a.b("attendees", 10, nt.class));
        ae.put("audio", ji.a.a("audio", 11, nt.class));
        ae.put("author", ji.a.b("author", 12, nt.class));
        ae.put("bestRating", ji.a.d("bestRating", 13));
        ae.put("birthDate", ji.a.d("birthDate", 14));
        ae.put("byArtist", ji.a.a("byArtist", 15, nt.class));
        ae.put("caption", ji.a.d("caption", 16));
        ae.put("contentSize", ji.a.d("contentSize", 17));
        ae.put("contentUrl", ji.a.d("contentUrl", 18));
        ae.put("contributor", ji.a.b("contributor", 19, nt.class));
        ae.put("dateCreated", ji.a.d("dateCreated", 20));
        ae.put("dateModified", ji.a.d("dateModified", 21));
        ae.put("datePublished", ji.a.d("datePublished", 22));
        ae.put("description", ji.a.d("description", 23));
        ae.put("duration", ji.a.d("duration", 24));
        ae.put("embedUrl", ji.a.d("embedUrl", 25));
        ae.put("endDate", ji.a.d("endDate", 26));
        ae.put("familyName", ji.a.d("familyName", 27));
        ae.put("gender", ji.a.d("gender", 28));
        ae.put("geo", ji.a.a("geo", 29, nt.class));
        ae.put("givenName", ji.a.d("givenName", 30));
        ae.put("height", ji.a.d("height", 31));
        ae.put("id", ji.a.d("id", 32));
        ae.put(Utilities.DB_KEY_IMAGE, ji.a.d(Utilities.DB_KEY_IMAGE, 33));
        ae.put("inAlbum", ji.a.a("inAlbum", 34, nt.class));
        ae.put("latitude", ji.a.b("latitude", 36));
        ae.put(Headers.LOCATION, ji.a.a(Headers.LOCATION, 37, nt.class));
        ae.put("longitude", ji.a.b("longitude", 38));
        ae.put(Utilities.DB_KEY_IMAGE_NAME, ji.a.d(Utilities.DB_KEY_IMAGE_NAME, 39));
        ae.put("partOfTVSeries", ji.a.a("partOfTVSeries", 40, nt.class));
        ae.put("performers", ji.a.b("performers", 41, nt.class));
        ae.put("playerType", ji.a.d("playerType", 42));
        ae.put("postOfficeBoxNumber", ji.a.d("postOfficeBoxNumber", 43));
        ae.put("postalCode", ji.a.d("postalCode", 44));
        ae.put("ratingValue", ji.a.d("ratingValue", 45));
        ae.put("reviewRating", ji.a.a("reviewRating", 46, nt.class));
        ae.put("startDate", ji.a.d("startDate", 47));
        ae.put("streetAddress", ji.a.d("streetAddress", 48));
        ae.put("text", ji.a.d("text", 49));
        ae.put("thumbnail", ji.a.a("thumbnail", 50, nt.class));
        ae.put("thumbnailUrl", ji.a.d("thumbnailUrl", 51));
        ae.put("tickerSymbol", ji.a.d("tickerSymbol", 52));
        ae.put("type", ji.a.d("type", 53));
        ae.put(NativeProtocol.IMAGE_URL_KEY, ji.a.d(NativeProtocol.IMAGE_URL_KEY, 54));
        ae.put("width", ji.a.d("width", 55));
        ae.put("worstRating", ji.a.d("worstRating", 56));
    }

    public nt() {
        this.b = 1;
        this.a = new HashSet();
    }

    public nt(Set<Integer> set, int i, nt ntVar, List<String> list, nt ntVar2, String str, String str2, String str3, List<nt> list2, int i2, List<nt> list3, nt ntVar3, List<nt> list4, String str4, String str5, nt ntVar4, String str6, String str7, String str8, List<nt> list5, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, nt ntVar5, String str18, String str19, String str20, String str21, nt ntVar6, double d, nt ntVar7, double d2, String str22, nt ntVar8, List<nt> list6, String str23, String str24, String str25, String str26, nt ntVar9, String str27, String str28, String str29, nt ntVar10, String str30, String str31, String str32, String str33, String str34, String str35) {
        this.a = set;
        this.b = i;
        this.c = ntVar;
        this.d = list;
        this.e = ntVar2;
        this.f = str;
        this.g = str2;
        this.h = str3;
        this.i = list2;
        this.j = i2;
        this.k = list3;
        this.l = ntVar3;
        this.m = list4;
        this.n = str4;
        this.o = str5;
        this.p = ntVar4;
        this.q = str6;
        this.r = str7;
        this.s = str8;
        this.t = list5;
        this.u = str9;
        this.v = str10;
        this.w = str11;
        this.x = str12;
        this.y = str13;
        this.z = str14;
        this.A = str15;
        this.B = str16;
        this.C = str17;
        this.D = ntVar5;
        this.E = str18;
        this.F = str19;
        this.G = str20;
        this.H = str21;
        this.I = ntVar6;
        this.J = d;
        this.K = ntVar7;
        this.L = d2;
        this.M = str22;
        this.N = ntVar8;
        this.O = list6;
        this.P = str23;
        this.Q = str24;
        this.R = str25;
        this.S = str26;
        this.T = ntVar9;
        this.U = str27;
        this.V = str28;
        this.W = str29;
        this.X = ntVar10;
        this.Y = str30;
        this.Z = str31;
        this.aa = str32;
        this.ab = str33;
        this.ac = str34;
        this.ad = str35;
    }

    @Override // com.google.android.gms.internal.ji
    protected boolean a(ji.a aVar) {
        return this.a.contains(Integer.valueOf(aVar.g()));
    }

    @Override // com.google.android.gms.internal.ji
    protected Object b(ji.a aVar) {
        switch (aVar.g()) {
            case 2:
                return this.c;
            case 3:
                return this.d;
            case 4:
                return this.e;
            case 5:
                return this.f;
            case 6:
                return this.g;
            case 7:
                return this.h;
            case 8:
                return this.i;
            case 9:
                return Integer.valueOf(this.j);
            case 10:
                return this.k;
            case 11:
                return this.l;
            case DateTimeConstants.DECEMBER /* 12 */:
                return this.m;
            case HTTP.CR /* 13 */:
                return this.n;
            case 14:
                return this.o;
            case ViewDragHelper.EDGE_ALL /* 15 */:
                return this.p;
            case 16:
                return this.q;
            case LangUtils.HASH_SEED /* 17 */:
                return this.r;
            case 18:
                return this.s;
            case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                return this.t;
            case 20:
                return this.u;
            case 21:
                return this.v;
            case 22:
                return this.w;
            case 23:
                return this.x;
            case DateTimeConstants.HOURS_PER_DAY /* 24 */:
                return this.y;
            case 25:
                return this.z;
            case 26:
                return this.A;
            case 27:
                return this.B;
            case 28:
                return this.C;
            case 29:
                return this.D;
            case 30:
                return this.E;
            case 31:
                return this.F;
            case 32:
                return this.G;
            case 33:
                return this.H;
            case 34:
                return this.I;
            case 35:
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + aVar.g());
            case 36:
                return Double.valueOf(this.J);
            case LangUtils.HASH_OFFSET /* 37 */:
                return this.K;
            case 38:
                return Double.valueOf(this.L);
            case 39:
                return this.M;
            case 40:
                return this.N;
            case 41:
                return this.O;
            case 42:
                return this.P;
            case 43:
                return this.Q;
            case 44:
                return this.R;
            case 45:
                return this.S;
            case 46:
                return this.T;
            case 47:
                return this.U;
            case 48:
                return this.V;
            case 49:
                return this.W;
            case 50:
                return this.X;
            case 51:
                return this.Y;
            case 52:
                return this.Z;
            case 53:
                return this.aa;
            case 54:
                return this.ab;
            case 55:
                return this.ac;
            case 56:
                return this.ad;
        }
    }

    @Override // com.google.android.gms.internal.ji
    public HashMap<String, ji.a<?, ?>> b() {
        return ae;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        ain ainVar = CREATOR;
        return 0;
    }

    @Override // defpackage.ik
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public nt a() {
        return this;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof nt)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        nt ntVar = (nt) obj;
        for (ji.a<?, ?> aVar : ae.values()) {
            if (a(aVar)) {
                if (ntVar.a(aVar) && b(aVar).equals(ntVar.b(aVar))) {
                }
                return false;
            }
            if (ntVar.a(aVar)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        Iterator<ji.a<?, ?>> it = ae.values().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            ji.a<?, ?> next = it.next();
            if (a(next)) {
                i = b(next).hashCode() + i2 + next.g();
            } else {
                i = i2;
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ain ainVar = CREATOR;
        ain.a(this, parcel, i);
    }
}
