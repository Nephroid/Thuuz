package defpackage;

import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class tf implements Comparator<String> {
    final /* synthetic */ te a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public tf(te teVar) {
        this.a = teVar;
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(String str, String str2) {
        return str2.length() - str.length();
    }
}
