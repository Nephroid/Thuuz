package defpackage;

import android.os.Bundle;
import java.util.Date;

/* loaded from: classes.dex */
public class mm extends mg<Date> {
    public mm(String str, int i) {
        super(str, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.md
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Date b(Bundle bundle) {
        return new Date(bundle.getLong(a()));
    }
}
