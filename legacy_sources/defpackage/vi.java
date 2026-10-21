package defpackage;
// [removed legacy tracker import]
import com.google.android.gms.internal.gv;
import java.util.Map;

@zy
/* loaded from: classes.dex */
public class vi implements vg {
    private final vj a;

    public vi(vj vjVar) {
        this.a = vjVar;
    }

    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        this.a.a(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("transparentBackground")));
    }
}
