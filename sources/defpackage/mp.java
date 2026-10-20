package defpackage;

import android.os.Bundle;

/* loaded from: classes.dex */
public class mp extends md<Long> {
    public mp(String str, int i) {
        super(str, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.md
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Long b(Bundle bundle) {
        return Long.valueOf(bundle.getLong(a()));
    }
}
