package defpackage;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.internal.fi;

@zy
/* loaded from: classes.dex */
public final class abd extends abc {
    private final Context a;

    public abd(Context context, fi fiVar, abb abbVar) {
        super(fiVar, abbVar);
        this.a = context;
    }

    @Override // defpackage.abc
    public void c() {
    }

    @Override // defpackage.abc
    public abj d() {
        Bundle m = acl.m();
        return abu.a(this.a, new uj(m.getString("gads:sdk_core_location"), m.getString("gads:sdk_core_experiment_id"), m.getString("gads:block_autoclicks_experiment_id")), new vv(), new acf());
    }
}
