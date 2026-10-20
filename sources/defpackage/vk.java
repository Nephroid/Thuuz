package defpackage;

import com.google.android.gms.internal.gv;
import java.util.HashMap;
import java.util.Map;

@zy
/* loaded from: classes.dex */
public class vk implements vg {
    static final Map<String, Integer> a = new HashMap();

    static {
        a.put("resize", 1);
        a.put("playVideo", 2);
        a.put("storePicture", 3);
        a.put("createCalendarEvent", 4);
    }

    @Override // defpackage.vg
    public void a(gv gvVar, Map<String, String> map) {
        switch (a.get(map.get("a")).intValue()) {
            case 1:
                new xc(gvVar, map).b();
                return;
            case 2:
            default:
                adn.c("Unknown MRAID command called.");
                return;
            case 3:
                new xd(gvVar, map).a();
                return;
            case 4:
                new wz(gvVar, map).a();
                return;
        }
    }
}
