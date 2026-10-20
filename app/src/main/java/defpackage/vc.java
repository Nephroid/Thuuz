package defpackage;
// [removed legacy tracker import]
import com.google.android.gms.internal.gv;
import java.util.Map;

/* loaded from: classes.dex */
final class vc implements vg {
    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        gvVar.b(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close")));
    }
}
