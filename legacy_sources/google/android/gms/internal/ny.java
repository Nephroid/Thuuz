package com.google.android.gms.internal;

import android.net.http.Headers;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ViewDragHelper;
// [removed legacy tracker import]
import com.google.android.gms.internal.ji;
import com.samsung.android.sdk.richnotification.Utilities;
import defpackage.aip;
import defpackage.aiq;
import defpackage.air;
import defpackage.ais;
import defpackage.ait;
import defpackage.aiu;
import defpackage.aiv;
import defpackage.aiw;
import defpackage.aix;
import defpackage.aiy;
import defpackage.arc;
import defpackage.ard;
import defpackage.are;
import defpackage.arf;
import defpackage.arg;
import defpackage.arh;
import defpackage.ari;
import defpackage.arj;
import defpackage.ark;
import defpackage.arl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public final class ny extends jj implements arc {
    public final Set<Integer> a;
    public final int b;
    public String c;
    public a d;
    public String e;
    public String f;
    public int g;
    public b h;
    public String i;
    public String j;
    public int k;
    public String l;
    public c m;
    public boolean n;
    public String o;
    public d p;
    public String q;
    public int r;
    public List<f> s;
    public List<g> t;
    public int u;
    public int v;
    public String w;
    public String x;
    public List<h> y;
    public boolean z;
    public static final aip CREATOR = new aip();
    private static final HashMap<String, ji.a<?, ?>> A = new HashMap<>();

    /* loaded from: classes.dex */
    public final class a extends jj implements ard {
        public static final aiq CREATOR = new aiq();
        private static final HashMap<String, ji.a<?, ?>> e = new HashMap<>();
        public final Set<Integer> a;
        public final int b;
        public int c;
        public int d;

        static {
            e.put("max", ji.a.a("max", 2));
            e.put("min", ji.a.a("min", 3));
        }

        public a() {
            this.b = 1;
            this.a = new HashSet();
        }

        public a(Set<Integer> set, int i, int i2, int i3) {
            this.a = set;
            this.b = i;
            this.c = i2;
            this.d = i3;
        }

        @Override // com.google.android.gms.internal.ji
        protected boolean a(ji.a aVar) {
            return this.a.contains(Integer.valueOf(aVar.g()));
        }

        @Override // com.google.android.gms.internal.ji
        protected Object b(ji.a aVar) {
            switch (aVar.g()) {
                case 2:
                    return Integer.valueOf(this.c);
                case 3:
                    return Integer.valueOf(this.d);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.g());
            }
        }

        @Override // com.google.android.gms.internal.ji
        public HashMap<String, ji.a<?, ?>> b() {
            return e;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            aiq aiqVar = CREATOR;
            return 0;
        }

        @Override // defpackage.ik
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public a a() {
            return this;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            a aVar = (a) obj;
            for (ji.a<?, ?> aVar2 : e.values()) {
                if (a(aVar2)) {
                    if (aVar.a(aVar2) && b(aVar2).equals(aVar.b(aVar2))) {
                    }
                    return false;
                }
                if (aVar.a(aVar2)) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i = 0;
            Iterator<ji.a<?, ?>> it = e.values().iterator();
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
            aiq aiqVar = CREATOR;
            aiq.a(this, parcel, i);
        }
    }

    /* loaded from: classes.dex */
    public final class b extends jj implements are {
        public static final air CREATOR = new air();
        private static final HashMap<String, ji.a<?, ?>> f = new HashMap<>();
        public final Set<Integer> a;
        public final int b;
        public a c;
        public C0004b d;
        public int e;

        /* loaded from: classes.dex */
        public final class a extends jj implements arf {
            public static final ais CREATOR = new ais();
            private static final HashMap<String, ji.a<?, ?>> e = new HashMap<>();
            public final Set<Integer> a;
            public final int b;
            public int c;
            public int d;

            static {
                e.put("leftImageOffset", ji.a.a("leftImageOffset", 2));
                e.put("topImageOffset", ji.a.a("topImageOffset", 3));
            }

            public a() {
                this.b = 1;
                this.a = new HashSet();
            }

            public a(Set<Integer> set, int i, int i2, int i3) {
                this.a = set;
                this.b = i;
                this.c = i2;
                this.d = i3;
            }

            @Override // com.google.android.gms.internal.ji
            protected boolean a(ji.a aVar) {
                return this.a.contains(Integer.valueOf(aVar.g()));
            }

            @Override // com.google.android.gms.internal.ji
            protected Object b(ji.a aVar) {
                switch (aVar.g()) {
                    case 2:
                        return Integer.valueOf(this.c);
                    case 3:
                        return Integer.valueOf(this.d);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + aVar.g());
                }
            }

            @Override // com.google.android.gms.internal.ji
            public HashMap<String, ji.a<?, ?>> b() {
                return e;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                ais aisVar = CREATOR;
                return 0;
            }

            @Override // defpackage.ik
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public a a() {
                return this;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof a)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                a aVar = (a) obj;
                for (ji.a<?, ?> aVar2 : e.values()) {
                    if (a(aVar2)) {
                        if (aVar.a(aVar2) && b(aVar2).equals(aVar.b(aVar2))) {
                        }
                        return false;
                    }
                    if (aVar.a(aVar2)) {
                        return false;
                    }
                }
                return true;
            }

            public int hashCode() {
                int i = 0;
                Iterator<ji.a<?, ?>> it = e.values().iterator();
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
                ais aisVar = CREATOR;
                ais.a(this, parcel, i);
            }
        }

        /* renamed from: com.google.android.gms.internal.ny$b$b, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public final class C0004b extends jj implements arg {
            public static final ait CREATOR = new ait();
            private static final HashMap<String, ji.a<?, ?>> f = new HashMap<>();
            public final Set<Integer> a;
            public final int b;
            public int c;
            public String d;
            public int e;

            static {
                f.put("height", ji.a.a("height", 2));
                f.put(NativeProtocol.IMAGE_URL_KEY, ji.a.d(NativeProtocol.IMAGE_URL_KEY, 3));
                f.put("width", ji.a.a("width", 4));
            }

            public C0004b() {
                this.b = 1;
                this.a = new HashSet();
            }

            public C0004b(Set<Integer> set, int i, int i2, String str, int i3) {
                this.a = set;
                this.b = i;
                this.c = i2;
                this.d = str;
                this.e = i3;
            }

            @Override // com.google.android.gms.internal.ji
            protected boolean a(ji.a aVar) {
                return this.a.contains(Integer.valueOf(aVar.g()));
            }

            @Override // com.google.android.gms.internal.ji
            protected Object b(ji.a aVar) {
                switch (aVar.g()) {
                    case 2:
                        return Integer.valueOf(this.c);
                    case 3:
                        return this.d;
                    case 4:
                        return Integer.valueOf(this.e);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + aVar.g());
                }
            }

            @Override // com.google.android.gms.internal.ji
            public HashMap<String, ji.a<?, ?>> b() {
                return f;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                ait aitVar = CREATOR;
                return 0;
            }

            @Override // defpackage.ik
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public C0004b a() {
                return this;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof C0004b)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                C0004b c0004b = (C0004b) obj;
                for (ji.a<?, ?> aVar : f.values()) {
                    if (a(aVar)) {
                        if (c0004b.a(aVar) && b(aVar).equals(c0004b.b(aVar))) {
                        }
                        return false;
                    }
                    if (c0004b.a(aVar)) {
                        return false;
                    }
                }
                return true;
            }

            public int hashCode() {
                int i = 0;
                Iterator<ji.a<?, ?>> it = f.values().iterator();
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
                ait aitVar = CREATOR;
                ait.a(this, parcel, i);
            }
        }

        static {
            f.put("coverInfo", ji.a.a("coverInfo", 2, a.class));
            f.put("coverPhoto", ji.a.a("coverPhoto", 3, C0004b.class));
            f.put("layout", ji.a.a("layout", 4, new jf().a("banner", 0), false));
        }

        public b() {
            this.b = 1;
            this.a = new HashSet();
        }

        public b(Set<Integer> set, int i, a aVar, C0004b c0004b, int i2) {
            this.a = set;
            this.b = i;
            this.c = aVar;
            this.d = c0004b;
            this.e = i2;
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
                    return Integer.valueOf(this.e);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.g());
            }
        }

        @Override // com.google.android.gms.internal.ji
        public HashMap<String, ji.a<?, ?>> b() {
            return f;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            air airVar = CREATOR;
            return 0;
        }

        @Override // defpackage.ik
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public b a() {
            return this;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            b bVar = (b) obj;
            for (ji.a<?, ?> aVar : f.values()) {
                if (a(aVar)) {
                    if (bVar.a(aVar) && b(aVar).equals(bVar.b(aVar))) {
                    }
                    return false;
                }
                if (bVar.a(aVar)) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i = 0;
            Iterator<ji.a<?, ?>> it = f.values().iterator();
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
            air airVar = CREATOR;
            air.a(this, parcel, i);
        }
    }

    /* loaded from: classes.dex */
    public final class c extends jj implements arh {
        public static final aiu CREATOR = new aiu();
        private static final HashMap<String, ji.a<?, ?>> d = new HashMap<>();
        public final Set<Integer> a;
        public final int b;
        public String c;

        static {
            d.put(NativeProtocol.IMAGE_URL_KEY, ji.a.d(NativeProtocol.IMAGE_URL_KEY, 2));
        }

        public c() {
            this.b = 1;
            this.a = new HashSet();
        }

        public c(Set<Integer> set, int i, String str) {
            this.a = set;
            this.b = i;
            this.c = str;
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
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.g());
            }
        }

        @Override // com.google.android.gms.internal.ji
        public HashMap<String, ji.a<?, ?>> b() {
            return d;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            aiu aiuVar = CREATOR;
            return 0;
        }

        @Override // defpackage.ik
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public c a() {
            return this;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof c)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            c cVar = (c) obj;
            for (ji.a<?, ?> aVar : d.values()) {
                if (a(aVar)) {
                    if (cVar.a(aVar) && b(aVar).equals(cVar.b(aVar))) {
                    }
                    return false;
                }
                if (cVar.a(aVar)) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i = 0;
            Iterator<ji.a<?, ?>> it = d.values().iterator();
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
            aiu aiuVar = CREATOR;
            aiu.a(this, parcel, i);
        }
    }

    /* loaded from: classes.dex */
    public final class d extends jj implements ari {
        public static final aiv CREATOR = new aiv();
        private static final HashMap<String, ji.a<?, ?>> i = new HashMap<>();
        public final Set<Integer> a;
        public final int b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;

        static {
            i.put("familyName", ji.a.d("familyName", 2));
            i.put("formatted", ji.a.d("formatted", 3));
            i.put("givenName", ji.a.d("givenName", 4));
            i.put("honorificPrefix", ji.a.d("honorificPrefix", 5));
            i.put("honorificSuffix", ji.a.d("honorificSuffix", 6));
            i.put("middleName", ji.a.d("middleName", 7));
        }

        public d() {
            this.b = 1;
            this.a = new HashSet();
        }

        public d(Set<Integer> set, int i2, String str, String str2, String str3, String str4, String str5, String str6) {
            this.a = set;
            this.b = i2;
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f = str4;
            this.g = str5;
            this.h = str6;
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
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.g());
            }
        }

        @Override // com.google.android.gms.internal.ji
        public HashMap<String, ji.a<?, ?>> b() {
            return i;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            aiv aivVar = CREATOR;
            return 0;
        }

        @Override // defpackage.ik
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public d a() {
            return this;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof d)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            d dVar = (d) obj;
            for (ji.a<?, ?> aVar : i.values()) {
                if (a(aVar)) {
                    if (dVar.a(aVar) && b(aVar).equals(dVar.b(aVar))) {
                    }
                    return false;
                }
                if (dVar.a(aVar)) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i2 = 0;
            Iterator<ji.a<?, ?>> it = i.values().iterator();
            while (true) {
                int i3 = i2;
                if (!it.hasNext()) {
                    return i3;
                }
                ji.a<?, ?> next = it.next();
                if (a(next)) {
                    i2 = b(next).hashCode() + i3 + next.g();
                } else {
                    i2 = i3;
                }
            }
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            aiv aivVar = CREATOR;
            aiv.a(this, parcel, i2);
        }
    }

    /* loaded from: classes.dex */
    public final class f extends jj implements arj {
        public static final aiw CREATOR = new aiw();
        private static final HashMap<String, ji.a<?, ?>> l = new HashMap<>();
        public final Set<Integer> a;
        public final int b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public boolean h;
        public String i;
        public String j;
        public int k;

        static {
            l.put("department", ji.a.d("department", 2));
            l.put("description", ji.a.d("description", 3));
            l.put("endDate", ji.a.d("endDate", 4));
            l.put(Headers.LOCATION, ji.a.d(Headers.LOCATION, 5));
            l.put(Utilities.DB_KEY_IMAGE_NAME, ji.a.d(Utilities.DB_KEY_IMAGE_NAME, 6));
            l.put("primary", ji.a.c("primary", 7));
            l.put("startDate", ji.a.d("startDate", 8));
            l.put("title", ji.a.d("title", 9));
            l.put("type", ji.a.a("type", 10, new jf().a("work", 0).a("school", 1), false));
        }

        public f() {
            this.b = 1;
            this.a = new HashSet();
        }

        public f(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, int i2) {
            this.a = set;
            this.b = i;
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f = str4;
            this.g = str5;
            this.h = z;
            this.i = str6;
            this.j = str7;
            this.k = i2;
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
                    return Boolean.valueOf(this.h);
                case 8:
                    return this.i;
                case 9:
                    return this.j;
                case 10:
                    return Integer.valueOf(this.k);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.g());
            }
        }

        @Override // com.google.android.gms.internal.ji
        public HashMap<String, ji.a<?, ?>> b() {
            return l;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            aiw aiwVar = CREATOR;
            return 0;
        }

        @Override // defpackage.ik
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public f a() {
            return this;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof f)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            f fVar = (f) obj;
            for (ji.a<?, ?> aVar : l.values()) {
                if (a(aVar)) {
                    if (fVar.a(aVar) && b(aVar).equals(fVar.b(aVar))) {
                    }
                    return false;
                }
                if (fVar.a(aVar)) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i = 0;
            Iterator<ji.a<?, ?>> it = l.values().iterator();
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
            aiw aiwVar = CREATOR;
            aiw.a(this, parcel, i);
        }
    }

    /* loaded from: classes.dex */
    public final class g extends jj implements ark {
        public static final aix CREATOR = new aix();
        private static final HashMap<String, ji.a<?, ?>> e = new HashMap<>();
        public final Set<Integer> a;
        public final int b;
        public boolean c;
        public String d;

        static {
            e.put("primary", ji.a.c("primary", 2));
            e.put("value", ji.a.d("value", 3));
        }

        public g() {
            this.b = 1;
            this.a = new HashSet();
        }

        public g(Set<Integer> set, int i, boolean z, String str) {
            this.a = set;
            this.b = i;
            this.c = z;
            this.d = str;
        }

        @Override // com.google.android.gms.internal.ji
        protected boolean a(ji.a aVar) {
            return this.a.contains(Integer.valueOf(aVar.g()));
        }

        @Override // com.google.android.gms.internal.ji
        protected Object b(ji.a aVar) {
            switch (aVar.g()) {
                case 2:
                    return Boolean.valueOf(this.c);
                case 3:
                    return this.d;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.g());
            }
        }

        @Override // com.google.android.gms.internal.ji
        public HashMap<String, ji.a<?, ?>> b() {
            return e;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            aix aixVar = CREATOR;
            return 0;
        }

        @Override // defpackage.ik
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public g a() {
            return this;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof g)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            g gVar = (g) obj;
            for (ji.a<?, ?> aVar : e.values()) {
                if (a(aVar)) {
                    if (gVar.a(aVar) && b(aVar).equals(gVar.b(aVar))) {
                    }
                    return false;
                }
                if (gVar.a(aVar)) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i = 0;
            Iterator<ji.a<?, ?>> it = e.values().iterator();
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
            aix aixVar = CREATOR;
            aix.a(this, parcel, i);
        }
    }

    /* loaded from: classes.dex */
    public final class h extends jj implements arl {
        public static final aiy CREATOR = new aiy();
        private static final HashMap<String, ji.a<?, ?>> f = new HashMap<>();
        public final Set<Integer> a;
        public final int b;
        public String c;
        public int d;
        public String e;
        private final int g;

        static {
            f.put("label", ji.a.d("label", 5));
            f.put("type", ji.a.a("type", 6, new jf().a("home", 0).a("work", 1).a("blog", 2).a("profile", 3).a("other", 4).a("otherProfile", 5).a("contributor", 6).a("website", 7), false));
            f.put("value", ji.a.d("value", 4));
        }

        public h() {
            this.g = 4;
            this.b = 1;
            this.a = new HashSet();
        }

        public h(Set<Integer> set, int i, String str, int i2, String str2, int i3) {
            this.g = 4;
            this.a = set;
            this.b = i;
            this.c = str;
            this.d = i2;
            this.e = str2;
        }

        @Override // com.google.android.gms.internal.ji
        protected boolean a(ji.a aVar) {
            return this.a.contains(Integer.valueOf(aVar.g()));
        }

        @Override // com.google.android.gms.internal.ji
        protected Object b(ji.a aVar) {
            switch (aVar.g()) {
                case 4:
                    return this.e;
                case 5:
                    return this.c;
                case 6:
                    return Integer.valueOf(this.d);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.g());
            }
        }

        @Override // com.google.android.gms.internal.ji
        public HashMap<String, ji.a<?, ?>> b() {
            return f;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            aiy aiyVar = CREATOR;
            return 0;
        }

        @Deprecated
        public int e() {
            return 4;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof h)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            h hVar = (h) obj;
            for (ji.a<?, ?> aVar : f.values()) {
                if (a(aVar)) {
                    if (hVar.a(aVar) && b(aVar).equals(hVar.b(aVar))) {
                    }
                    return false;
                }
                if (hVar.a(aVar)) {
                    return false;
                }
            }
            return true;
        }

        @Override // defpackage.ik
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public h a() {
            return this;
        }

        public int hashCode() {
            int i = 0;
            Iterator<ji.a<?, ?>> it = f.values().iterator();
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
            aiy aiyVar = CREATOR;
            aiy.a(this, parcel, i);
        }
    }

    static {
        A.put("aboutMe", ji.a.d("aboutMe", 2));
        A.put("ageRange", ji.a.a("ageRange", 3, a.class));
        A.put("birthday", ji.a.d("birthday", 4));
        A.put("braggingRights", ji.a.d("braggingRights", 5));
        A.put("circledByCount", ji.a.a("circledByCount", 6));
        A.put("cover", ji.a.a("cover", 7, b.class));
        A.put("currentLocation", ji.a.d("currentLocation", 8));
        A.put("displayName", ji.a.d("displayName", 9));
        A.put("gender", ji.a.a("gender", 12, new jf().a("male", 0).a("female", 1).a("other", 2), false));
        A.put("id", ji.a.d("id", 14));
        A.put(Utilities.DB_KEY_IMAGE, ji.a.a(Utilities.DB_KEY_IMAGE, 15, c.class));
        A.put("isPlusUser", ji.a.c("isPlusUser", 16));
        A.put("language", ji.a.d("language", 18));
        A.put(Utilities.DB_KEY_IMAGE_NAME, ji.a.a(Utilities.DB_KEY_IMAGE_NAME, 19, d.class));
        A.put("nickname", ji.a.d("nickname", 20));
        A.put("objectType", ji.a.a("objectType", 21, new jf().a("person", 0).a("page", 1), false));
        A.put("organizations", ji.a.b("organizations", 22, f.class));
        A.put("placesLived", ji.a.b("placesLived", 23, g.class));
        A.put("plusOneCount", ji.a.a("plusOneCount", 24));
        A.put("relationshipStatus", ji.a.a("relationshipStatus", 25, new jf().a("single", 0).a("in_a_relationship", 1).a("engaged", 2).a("married", 3).a("its_complicated", 4).a("open_relationship", 5).a("widowed", 6).a("in_domestic_partnership", 7).a("in_civil_union", 8), false));
        A.put("tagline", ji.a.d("tagline", 26));
        A.put(NativeProtocol.IMAGE_URL_KEY, ji.a.d(NativeProtocol.IMAGE_URL_KEY, 27));
        A.put("urls", ji.a.b("urls", 28, h.class));
        A.put("verified", ji.a.c("verified", 29));
    }

    public ny() {
        this.b = 1;
        this.a = new HashSet();
    }

    public ny(Set<Integer> set, int i, String str, a aVar, String str2, String str3, int i2, b bVar, String str4, String str5, int i3, String str6, c cVar, boolean z, String str7, d dVar, String str8, int i4, List<f> list, List<g> list2, int i5, int i6, String str9, String str10, List<h> list3, boolean z2) {
        this.a = set;
        this.b = i;
        this.c = str;
        this.d = aVar;
        this.e = str2;
        this.f = str3;
        this.g = i2;
        this.h = bVar;
        this.i = str4;
        this.j = str5;
        this.k = i3;
        this.l = str6;
        this.m = cVar;
        this.n = z;
        this.o = str7;
        this.p = dVar;
        this.q = str8;
        this.r = i4;
        this.s = list;
        this.t = list2;
        this.u = i5;
        this.v = i6;
        this.w = str9;
        this.x = str10;
        this.y = list3;
        this.z = z2;
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
                return Integer.valueOf(this.g);
            case 7:
                return this.h;
            case 8:
                return this.i;
            case 9:
                return this.j;
            case 10:
            case 11:
            case HTTP.CR /* 13 */:
            case LangUtils.HASH_SEED /* 17 */:
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + aVar.g());
            case DateTimeConstants.DECEMBER /* 12 */:
                return Integer.valueOf(this.k);
            case 14:
                return this.l;
            case ViewDragHelper.EDGE_ALL /* 15 */:
                return this.m;
            case 16:
                return Boolean.valueOf(this.n);
            case 18:
                return this.o;
            case TimeUtils.HUNDRED_DAY_FIELD_LEN /* 19 */:
                return this.p;
            case 20:
                return this.q;
            case 21:
                return Integer.valueOf(this.r);
            case 22:
                return this.s;
            case 23:
                return this.t;
            case DateTimeConstants.HOURS_PER_DAY /* 24 */:
                return Integer.valueOf(this.u);
            case 25:
                return Integer.valueOf(this.v);
            case 26:
                return this.w;
            case 27:
                return this.x;
            case 28:
                return this.y;
            case 29:
                return Boolean.valueOf(this.z);
        }
    }

    @Override // com.google.android.gms.internal.ji
    public HashMap<String, ji.a<?, ?>> b() {
        return A;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        aip aipVar = CREATOR;
        return 0;
    }

    @Override // defpackage.ik
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public ny a() {
        return this;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ny)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ny nyVar = (ny) obj;
        for (ji.a<?, ?> aVar : A.values()) {
            if (a(aVar)) {
                if (nyVar.a(aVar) && b(aVar).equals(nyVar.b(aVar))) {
                }
                return false;
            }
            if (nyVar.a(aVar)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        Iterator<ji.a<?, ?>> it = A.values().iterator();
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
        aip aipVar = CREATOR;
        aip.a(this, parcel, i);
    }
}
