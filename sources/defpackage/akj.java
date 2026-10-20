package defpackage;

import android.content.Context;
import android.os.Looper;
import com.actionbarsherlock.widget.ActivityChooserView;
import com.google.android.gms.common.internal.ClientSettings;

/* loaded from: classes.dex */
final class akj implements hj<ahq, hg> {
    @Override // defpackage.hj
    public int a() {
        return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    @Override // defpackage.hj
    public ahq a(Context context, Looper looper, ClientSettings clientSettings, hg hgVar, ho hoVar, hp hpVar) {
        return new ahq(context, looper, context.getPackageName(), hoVar, hpVar, "locationServices", clientSettings.a());
    }
}
