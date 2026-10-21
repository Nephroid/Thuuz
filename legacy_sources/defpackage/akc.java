package defpackage;
// [removed legacy tracker import]
import com.google.android.gms.internal.gv;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
@zy
/* loaded from: classes.dex */
public class akc implements sa {
    private gv a;

    public akc(gv gvVar) {
        this.a = gvVar;
    }

    @Override // defpackage.sa
    public void a(sd sdVar, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.a.a("onAdVisibilityChanged", hashMap);
    }
}
