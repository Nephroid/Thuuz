package defpackage;

import android.os.Bundle;
import java.util.Collection;
import java.util.Collections;

/* loaded from: classes.dex */
public class mt extends me<String> {
    public mt(String str, int i) {
        super(str, Collections.singleton(str), Collections.emptySet(), i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.md
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Collection<String> b(Bundle bundle) {
        return bundle.getStringArrayList(a());
    }
}
