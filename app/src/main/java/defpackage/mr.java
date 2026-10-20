package defpackage;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.Collection;
import java.util.Collections;

/* loaded from: classes.dex */
public class mr<T extends Parcelable> extends me<T> {
    public mr(String str, int i) {
        super(str, Collections.emptySet(), Collections.singleton(str), i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.md
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Collection<T> b(Bundle bundle) {
        return bundle.getParcelableArrayList(a());
    }
}
