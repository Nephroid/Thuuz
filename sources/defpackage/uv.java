package defpackage;

import com.google.android.gms.internal.gv;
import com.samsung.android.sdk.richnotification.Utilities;
import java.util.Map;

@zy
/* loaded from: classes.dex */
public final class uv implements vg {
    private final uw a;

    public uv(uw uwVar) {
        this.a = uwVar;
    }

    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        String str = map.get(Utilities.DB_KEY_IMAGE_NAME);
        if (str == null) {
            adn.e("App event with no name parameter.");
        } else {
            this.a.a(str, map.get("info"));
        }
    }
}
