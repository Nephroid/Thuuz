package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.ji;
import defpackage.aft;
import defpackage.afu;
import defpackage.afv;
import defpackage.agb;
import defpackage.agd;
import defpackage.kb;
import defpackage.ke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class jp extends ji implements SafeParcelable {
    public static final aft CREATOR = new aft();
    private final int a;
    private final Parcel b;
    private final int c = 2;
    private final jm d;
    private final String e;
    private int f;
    private int g;

    public jp(int i, Parcel parcel, jm jmVar) {
        this.a = i;
        this.b = (Parcel) kb.a(parcel);
        this.d = jmVar;
        if (this.d == null) {
            this.e = null;
        } else {
            this.e = this.d.d();
        }
        this.f = 2;
    }

    public static HashMap<String, String> a(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    private static HashMap<Integer, Map.Entry<String, ji.a<?, ?>>> a(HashMap<String, ji.a<?, ?>> hashMap) {
        HashMap<Integer, Map.Entry<String, ji.a<?, ?>>> hashMap2 = new HashMap<>();
        for (Map.Entry<String, ji.a<?, ?>> entry : hashMap.entrySet()) {
            hashMap2.put(Integer.valueOf(entry.getValue().g()), entry);
        }
        return hashMap2;
    }

    private void a(StringBuilder sb, int i, Object obj) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(obj);
                return;
            case 7:
                sb.append("\"").append(agb.a(obj.toString())).append("\"");
                return;
            case 8:
                sb.append("\"").append(afv.a((byte[]) obj)).append("\"");
                return;
            case 9:
                sb.append("\"").append(afv.b((byte[]) obj));
                sb.append("\"");
                return;
            case 10:
                agd.a(sb, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    private void a(StringBuilder sb, ji.a<?, ?> aVar, Parcel parcel, int i) {
        switch (aVar.d()) {
            case 0:
                a(sb, aVar, a(aVar, Integer.valueOf(a.g(parcel, i))));
                return;
            case 1:
                a(sb, aVar, a(aVar, a.k(parcel, i)));
                return;
            case 2:
                a(sb, aVar, a(aVar, Long.valueOf(a.i(parcel, i))));
                return;
            case 3:
                a(sb, aVar, a(aVar, Float.valueOf(a.l(parcel, i))));
                return;
            case 4:
                a(sb, aVar, a(aVar, Double.valueOf(a.m(parcel, i))));
                return;
            case 5:
                a(sb, aVar, a(aVar, a.n(parcel, i)));
                return;
            case 6:
                a(sb, aVar, a(aVar, Boolean.valueOf(a.c(parcel, i))));
                return;
            case 7:
                a(sb, aVar, a(aVar, a.o(parcel, i)));
                return;
            case 8:
            case 9:
                a(sb, aVar, a(aVar, a.r(parcel, i)));
                return;
            case 10:
                a(sb, aVar, a(aVar, a(a.q(parcel, i))));
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown field out type = " + aVar.d());
        }
    }

    private void a(StringBuilder sb, ji.a<?, ?> aVar, Object obj) {
        if (aVar.c()) {
            a(sb, aVar, (ArrayList<?>) obj);
        } else {
            a(sb, aVar.b(), obj);
        }
    }

    private void a(StringBuilder sb, ji.a<?, ?> aVar, ArrayList<?> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            a(sb, aVar.b(), arrayList.get(i));
        }
        sb.append("]");
    }

    private void a(StringBuilder sb, String str, ji.a<?, ?> aVar, Parcel parcel, int i) {
        sb.append("\"").append(str).append("\":");
        if (aVar.j()) {
            a(sb, aVar, parcel, i);
        } else {
            b(sb, aVar, parcel, i);
        }
    }

    private void a(StringBuilder sb, HashMap<String, ji.a<?, ?>> hashMap, Parcel parcel) {
        HashMap<Integer, Map.Entry<String, ji.a<?, ?>>> a = a(hashMap);
        sb.append('{');
        int b = a.b(parcel);
        boolean z = false;
        while (parcel.dataPosition() < b) {
            int a2 = a.a(parcel);
            Map.Entry<String, ji.a<?, ?>> entry = a.get(Integer.valueOf(a.a(a2)));
            if (entry != null) {
                if (z) {
                    sb.append(",");
                }
                a(sb, entry.getKey(), entry.getValue(), parcel, a2);
                z = true;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        sb.append('}');
    }

    private void b(StringBuilder sb, ji.a<?, ?> aVar, Parcel parcel, int i) {
        if (aVar.e()) {
            sb.append("[");
            switch (aVar.d()) {
                case 0:
                    afu.a(sb, a.u(parcel, i));
                    break;
                case 1:
                    afu.a(sb, a.w(parcel, i));
                    break;
                case 2:
                    afu.a(sb, a.v(parcel, i));
                    break;
                case 3:
                    afu.a(sb, a.x(parcel, i));
                    break;
                case 4:
                    afu.a(sb, a.y(parcel, i));
                    break;
                case 5:
                    afu.a(sb, a.z(parcel, i));
                    break;
                case 6:
                    afu.a(sb, a.t(parcel, i));
                    break;
                case 7:
                    afu.a(sb, a.A(parcel, i));
                    break;
                case 8:
                case 9:
                case 10:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case 11:
                    Parcel[] E = a.E(parcel, i);
                    int length = E.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 > 0) {
                            sb.append(",");
                        }
                        E[i2].setDataPosition(0);
                        a(sb, aVar.l(), E[i2]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            sb.append("]");
            return;
        }
        switch (aVar.d()) {
            case 0:
                sb.append(a.g(parcel, i));
                return;
            case 1:
                sb.append(a.k(parcel, i));
                return;
            case 2:
                sb.append(a.i(parcel, i));
                return;
            case 3:
                sb.append(a.l(parcel, i));
                return;
            case 4:
                sb.append(a.m(parcel, i));
                return;
            case 5:
                sb.append(a.n(parcel, i));
                return;
            case 6:
                sb.append(a.c(parcel, i));
                return;
            case 7:
                sb.append("\"").append(agb.a(a.o(parcel, i))).append("\"");
                return;
            case 8:
                sb.append("\"").append(afv.a(a.r(parcel, i))).append("\"");
                return;
            case 9:
                sb.append("\"").append(afv.b(a.r(parcel, i)));
                sb.append("\"");
                return;
            case 10:
                Bundle q = a.q(parcel, i);
                Set<String> keySet = q.keySet();
                keySet.size();
                sb.append("{");
                boolean z = true;
                for (String str : keySet) {
                    if (!z) {
                        sb.append(",");
                    }
                    sb.append("\"").append(str).append("\"");
                    sb.append(":");
                    sb.append("\"").append(agb.a(q.getString(str))).append("\"");
                    z = false;
                }
                sb.append("}");
                return;
            case 11:
                Parcel D = a.D(parcel, i);
                D.setDataPosition(0);
                a(sb, aVar.l(), D);
                return;
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    public int a() {
        return this.a;
    }

    @Override // com.google.android.gms.internal.ji
    protected Object a(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    @Override // com.google.android.gms.internal.ji
    public HashMap<String, ji.a<?, ?>> b() {
        if (this.d == null) {
            return null;
        }
        return this.d.a(this.e);
    }

    @Override // com.google.android.gms.internal.ji
    protected boolean b(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        aft aftVar = CREATOR;
        return 0;
    }

    public Parcel e() {
        switch (this.f) {
            case 0:
                this.g = ke.a(this.b);
                ke.a(this.b, this.g);
                this.f = 2;
                break;
            case 1:
                ke.a(this.b, this.g);
                this.f = 2;
                break;
        }
        return this.b;
    }

    public jm f() {
        switch (this.c) {
            case 0:
                return null;
            case 1:
                return this.d;
            case 2:
                return this.d;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.c);
        }
    }

    @Override // com.google.android.gms.internal.ji
    public String toString() {
        kb.a(this.d, "Cannot convert to JSON on client side.");
        Parcel e = e();
        e.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        a(sb, this.d.a(this.e), e);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aft aftVar = CREATOR;
        aft.a(this, parcel, i);
    }
}
