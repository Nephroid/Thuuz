package defpackage;

import android.os.Bundle;

/* loaded from: classes.dex */
public class mk extends md<Boolean> {
    public mk(String str, int i) {
        super(str, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.md
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Boolean b(Bundle bundle) {
        return Boolean.valueOf(bundle.getBoolean(a()));
    }
}
