package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.afo;
import defpackage.afp;
import defpackage.afv;
import defpackage.agb;
import defpackage.agd;
import defpackage.kb;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public abstract class ji {

    /* loaded from: classes.dex */
    public class a<I, O> implements SafeParcelable {
        public static final afp CREATOR = new afp();
        protected final int a;
        protected final boolean b;
        protected final int c;
        protected final boolean d;
        protected final String e;
        protected final int f;
        protected final Class<? extends ji> g;
        protected final String h;
        private final int i;
        private jm j;
        private afo<I, O> k;

        public a(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, jd jdVar) {
            this.i = i;
            this.a = i2;
            this.b = z;
            this.c = i3;
            this.d = z2;
            this.e = str;
            this.f = i4;
            if (str2 == null) {
                this.g = null;
                this.h = null;
            } else {
                this.g = jp.class;
                this.h = str2;
            }
            if (jdVar == null) {
                this.k = null;
            } else {
                this.k = (afo<I, O>) jdVar.c();
            }
        }

        protected a(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends ji> cls, afo<I, O> afoVar) {
            this.i = 1;
            this.a = i;
            this.b = z;
            this.c = i2;
            this.d = z2;
            this.e = str;
            this.f = i3;
            this.g = cls;
            if (cls == null) {
                this.h = null;
            } else {
                this.h = cls.getCanonicalName();
            }
            this.k = afoVar;
        }

        public static a<Integer, Integer> a(String str, int i) {
            return new a<>(0, false, 0, false, str, i, null, null);
        }

        public static a a(String str, int i, afo<?, ?> afoVar, boolean z) {
            return new a(afoVar.c(), z, afoVar.d(), false, str, i, null, afoVar);
        }

        public static <T extends ji> a<T, T> a(String str, int i, Class<T> cls) {
            return new a<>(11, false, 11, false, str, i, cls, null);
        }

        public static a<Double, Double> b(String str, int i) {
            return new a<>(4, false, 4, false, str, i, null, null);
        }

        public static <T extends ji> a<ArrayList<T>, ArrayList<T>> b(String str, int i, Class<T> cls) {
            return new a<>(11, true, 11, true, str, i, cls, null);
        }

        public static a<Boolean, Boolean> c(String str, int i) {
            return new a<>(6, false, 6, false, str, i, null, null);
        }

        public static a<String, String> d(String str, int i) {
            return new a<>(7, false, 7, false, str, i, null, null);
        }

        public static a<ArrayList<String>, ArrayList<String>> e(String str, int i) {
            return new a<>(7, true, 7, true, str, i, null, null);
        }

        public int a() {
            return this.i;
        }

        public I a(O o) {
            return this.k.a(o);
        }

        public void a(jm jmVar) {
            this.j = jmVar;
        }

        public int b() {
            return this.a;
        }

        public boolean c() {
            return this.b;
        }

        public int d() {
            return this.c;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            afp afpVar = CREATOR;
            return 0;
        }

        public boolean e() {
            return this.d;
        }

        public String f() {
            return this.e;
        }

        public int g() {
            return this.f;
        }

        public Class<? extends ji> h() {
            return this.g;
        }

        public String i() {
            if (this.h == null) {
                return null;
            }
            return this.h;
        }

        public boolean j() {
            return this.k != null;
        }

        public jd k() {
            if (this.k == null) {
                return null;
            }
            return jd.a(this.k);
        }

        public HashMap<String, a<?, ?>> l() {
            kb.a(this.h);
            kb.a(this.j);
            return this.j.a(this.h);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Field\n");
            sb.append("            versionCode=").append(this.i).append('\n');
            sb.append("                 typeIn=").append(this.a).append('\n');
            sb.append("            typeInArray=").append(this.b).append('\n');
            sb.append("                typeOut=").append(this.c).append('\n');
            sb.append("           typeOutArray=").append(this.d).append('\n');
            sb.append("        outputFieldName=").append(this.e).append('\n');
            sb.append("      safeParcelFieldId=").append(this.f).append('\n');
            sb.append("       concreteTypeName=").append(i()).append('\n');
            if (h() != null) {
                sb.append("     concreteType.class=").append(h().getCanonicalName()).append('\n');
            }
            sb.append("          converterName=").append(this.k == null ? "null" : this.k.getClass().getCanonicalName()).append('\n');
            return sb.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            afp afpVar = CREATOR;
            afp.a(this, parcel, i);
        }
    }

    private void a(StringBuilder sb, a aVar, Object obj) {
        if (aVar.b() == 11) {
            sb.append(aVar.h().cast(obj).toString());
        } else {
            if (aVar.b() != 7) {
                sb.append(obj);
                return;
            }
            sb.append("\"");
            sb.append(agb.a((String) obj));
            sb.append("\"");
        }
    }

    private void a(StringBuilder sb, a aVar, ArrayList<Object> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                a(sb, aVar, obj);
            }
        }
        sb.append("]");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public <O, I> I a(a<I, O> aVar, Object obj) {
        return ((a) aVar).k != null ? aVar.a((a<I, O>) obj) : obj;
    }

    protected abstract Object a(String str);

    protected boolean a(a aVar) {
        return aVar.d() == 11 ? aVar.e() ? d(aVar.f()) : c(aVar.f()) : b(aVar.f());
    }

    protected Object b(a aVar) {
        String f = aVar.f();
        if (aVar.h() == null) {
            return a(aVar.f());
        }
        kb.a(a(aVar.f()) == null, "Concrete field shouldn't be value object: %s", aVar.f());
        HashMap<String, Object> d = aVar.e() ? d() : c();
        if (d != null) {
            return d.get(f);
        }
        try {
            return getClass().getMethod("get" + Character.toUpperCase(f.charAt(0)) + f.substring(1), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract HashMap<String, a<?, ?>> b();

    protected abstract boolean b(String str);

    public HashMap<String, Object> c() {
        return null;
    }

    protected boolean c(String str) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    public HashMap<String, Object> d() {
        return null;
    }

    protected boolean d(String str) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }

    public String toString() {
        HashMap<String, a<?, ?>> b = b();
        StringBuilder sb = new StringBuilder(100);
        for (String str : b.keySet()) {
            a<?, ?> aVar = b.get(str);
            if (a(aVar)) {
                Object a2 = a(aVar, b(aVar));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"").append(str).append("\":");
                if (a2 != null) {
                    switch (aVar.d()) {
                        case 8:
                            sb.append("\"").append(afv.a((byte[]) a2)).append("\"");
                            break;
                        case 9:
                            sb.append("\"").append(afv.b((byte[]) a2)).append("\"");
                            break;
                        case 10:
                            agd.a(sb, (HashMap) a2);
                            break;
                        default:
                            if (aVar.c()) {
                                a(sb, (a) aVar, (ArrayList<Object>) a2);
                                break;
                            } else {
                                a(sb, aVar, a2);
                                break;
                            }
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }
}
