package defpackage;

import android.os.Bundle;

/* loaded from: classes.dex */
public class mo extends md<Integer> {
    public mo(String str, int i) {
        super(str, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.md
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Integer b(Bundle bundle) {
        return Integer.valueOf(bundle.getInt(a()));
    }
}
