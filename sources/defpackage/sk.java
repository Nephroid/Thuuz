package defpackage;

import com.facebook.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.internal.gv;
import java.util.Map;

/* loaded from: classes.dex */
class sk implements vg {
    final /* synthetic */ sd a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public sk(sd sdVar) {
        this.a = sdVar;
    }

    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        if (this.a.a(map) && map.containsKey("isVisible")) {
            this.a.a(Boolean.valueOf(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("isVisible")) || ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(map.get("isVisible"))).booleanValue());
        }
    }
}
