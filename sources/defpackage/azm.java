package defpackage;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.BitSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes.dex */
public final class azm {
    public static final axd<Class> a = new azn();
    public static final axe b = a(Class.class, a);
    public static final axd<BitSet> c = new azy();
    public static final axe d = a(BitSet.class, c);
    public static final axd<Boolean> e = new bak();
    public static final axd<Boolean> f = new ban();
    public static final axe g = a(Boolean.TYPE, Boolean.class, e);
    public static final axd<Number> h = new bao();
    public static final axe i = a(Byte.TYPE, Byte.class, h);
    public static final axd<Number> j = new bap();
    public static final axe k = a(Short.TYPE, Short.class, j);
    public static final axd<Number> l = new baq();
    public static final axe m = a(Integer.TYPE, Integer.class, l);
    public static final axd<Number> n = new bar();
    public static final axd<Number> o = new bas();
    public static final axd<Number> p = new azo();
    public static final axd<Number> q = new azp();
    public static final axe r = a(Number.class, q);
    public static final axd<Character> s = new azq();
    public static final axe t = a(Character.TYPE, Character.class, s);
    public static final axd<String> u = new azr();
    public static final axd<BigDecimal> v = new azs();
    public static final axd<BigInteger> w = new azt();
    public static final axe x = a(String.class, u);
    public static final axd<StringBuilder> y = new azu();
    public static final axe z = a(StringBuilder.class, y);
    public static final axd<StringBuffer> A = new azv();
    public static final axe B = a(StringBuffer.class, A);
    public static final axd<URL> C = new azw();
    public static final axe D = a(URL.class, C);
    public static final axd<URI> E = new azx();
    public static final axe F = a(URI.class, E);
    public static final axd<InetAddress> G = new azz();
    public static final axe H = b(InetAddress.class, G);
    public static final axd<UUID> I = new baa();
    public static final axe J = a(UUID.class, I);
    public static final axe K = new bab();
    public static final axd<Calendar> L = new bad();
    public static final axe M = b(Calendar.class, GregorianCalendar.class, L);
    public static final axd<Locale> N = new bae();
    public static final axe O = a(Locale.class, N);
    public static final axd<awu> P = new baf();
    public static final axe Q = b(awu.class, P);
    public static final axe R = a();

    public static axe a() {
        return new bag();
    }

    public static <TT> axe a(bau<TT> bauVar, axd<TT> axdVar) {
        return new bah(bauVar, axdVar);
    }

    public static <TT> axe a(Class<TT> cls, axd<TT> axdVar) {
        return new bai(cls, axdVar);
    }

    public static <TT> axe a(Class<TT> cls, Class<TT> cls2, axd<? super TT> axdVar) {
        return new baj(cls, cls2, axdVar);
    }

    public static <TT> axe b(Class<TT> cls, Class<? extends TT> cls2, axd<? super TT> axdVar) {
        return new bal(cls, cls2, axdVar);
    }

    public static <TT> axe b(Class<TT> cls, axd<TT> axdVar) {
        return new bam(cls, axdVar);
    }
}
