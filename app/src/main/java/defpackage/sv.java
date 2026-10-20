package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

@zy
/* loaded from: classes.dex */
public class sv {
    private final int a;
    private final int b;
    private final int c;
    private final te d;
    private int j;
    private final Object e = new Object();
    private ArrayList<String> f = new ArrayList<>();
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private String k = "";

    public sv(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = new te(i4);
    }

    private String a(ArrayList<String> arrayList, int i) {
        if (arrayList.isEmpty()) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
            stringBuffer.append(' ');
            if (stringBuffer.length() > i) {
                break;
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2.length() >= i ? stringBuffer2.substring(0, i) : stringBuffer2;
    }

    private void c(String str) {
        if (str == null || str.length() < this.c) {
            return;
        }
        synchronized (this.e) {
            this.f.add(str);
            this.g += str.length();
        }
    }

    int a(int i, int i2) {
        return (this.a * i) + (this.b * i2);
    }

    public void a(int i) {
        this.h = i;
    }

    public void a(String str) {
        c(str);
        synchronized (this.e) {
            if (this.i < 0) {
                adn.a("ActivityContent: negative number of WebViews.");
            }
            f();
        }
    }

    public boolean a() {
        boolean z;
        synchronized (this.e) {
            z = this.i == 0;
        }
        return z;
    }

    public String b() {
        return this.k;
    }

    public void b(String str) {
        c(str);
    }

    public void c() {
        synchronized (this.e) {
            this.j -= 100;
        }
    }

    public void d() {
        synchronized (this.e) {
            this.i--;
        }
    }

    public void e() {
        synchronized (this.e) {
            this.i++;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof sv)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        sv svVar = (sv) obj;
        return svVar.b() != null && svVar.b().equals(b());
    }

    public void f() {
        synchronized (this.e) {
            int a = a(this.g, this.h);
            if (a > this.j) {
                this.j = a;
                this.k = this.d.a(this.f);
            }
        }
    }

    public int g() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int h() {
        return this.g;
    }

    public int hashCode() {
        return b().hashCode();
    }

    public String toString() {
        return "ActivityContent fetchId: " + this.h + " score:" + this.j + " total_length:" + this.g + "\n text: " + a(this.f, 200) + "\n signture: " + this.k;
    }
}
