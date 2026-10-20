package defpackage;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.Collection;

/* loaded from: classes.dex */
public abstract class ms<T extends Parcelable> extends md<T> {
    public ms(String str, Collection<String> collection, Collection<String> collection2, int i) {
        super(str, collection, collection2, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.md
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public T b(Bundle bundle) {
        return (T) bundle.getParcelable(a());
    }
}
