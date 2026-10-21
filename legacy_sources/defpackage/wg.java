package defpackage;

import android.content.Context;
// [removed legacy tracker import]
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@zy
/* loaded from: classes.dex */
public final class wg {
    public static List<String> a(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(optJSONArray.length());
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.getString(i));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static void a(Context context, String str, acg acgVar, String str2, boolean z, List<String> list) {
        String str3 = z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO;
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String replaceAll = it.next().replaceAll("@gw_adlocid@", str2).replaceAll("@gw_adnetrefresh@", str3).replaceAll("@gw_qdata@", acgVar.o.f).replaceAll("@gw_sdkver@", str).replaceAll("@gw_sessid@", acl.a).replaceAll("@gw_seqnum@", acgVar.i);
            if (acgVar.l != null) {
                replaceAll = replaceAll.replaceAll("@gw_adnetid@", acgVar.l.b).replaceAll("@gw_allocid@", acgVar.l.d);
            }
            new adl(context, str, replaceAll).e();
        }
    }
}
