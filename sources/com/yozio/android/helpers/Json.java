package com.yozio.android.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Json {
    public static Object toJSON(Object obj) {
        if (obj instanceof Map) {
            JSONObject jSONObject = new JSONObject();
            Map map = (Map) obj;
            for (Object obj2 : map.keySet()) {
                jSONObject.put(obj2.toString(), toJSON(map.get(obj2)));
            }
            return jSONObject;
        }
        if (obj instanceof Iterable) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            return jSONArray;
        }
        return obj;
    }

    public static boolean isEmptyObject(JSONObject jSONObject) {
        return jSONObject.names() == null;
    }

    public static Map<String, Object> getMap(JSONObject jSONObject, String str) {
        return toMap(jSONObject.getJSONObject(str));
    }

    public static Map<String, Object> toMap(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, fromJson(jSONObject.get(next)));
        }
        return hashMap;
    }

    public static List toList(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(fromJson(jSONArray.get(i)));
        }
        return arrayList;
    }

    private static Object fromJson(Object obj) {
        if (obj == JSONObject.NULL) {
            return null;
        }
        if (obj instanceof JSONObject) {
            return toMap((JSONObject) obj);
        }
        if (obj instanceof JSONArray) {
            return toList((JSONArray) obj);
        }
        return obj;
    }
}
