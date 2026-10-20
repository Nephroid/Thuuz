package defpackage;

import org.json.JSONObject;

/* loaded from: classes.dex */
public class bpe extends bpq implements Comparable<bpe> {
    private int a;

    public bpe(JSONObject jSONObject) {
        super(jSONObject);
    }

    public int a() {
        return f("id");
    }

    public void a(int i) {
        a("id", i);
    }

    public String b() {
        return e("title");
    }

    public String c() {
        return e("subtitle");
    }

    public String d() {
        return e("price");
    }

    public int e() {
        return f("display_order");
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(bpe bpeVar) {
        return Integer.valueOf(e()).compareTo(Integer.valueOf(bpeVar.e()));
    }

    public boolean f() {
        return g("enabled");
    }

    public void b(int i) {
        this.a = i;
    }

    public int g() {
        return this.a;
    }
}
