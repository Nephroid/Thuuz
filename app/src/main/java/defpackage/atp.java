package defpackage;

import com.facebook.internal.ServerProtocol;
import com.google.android.gms.tagmanager.dh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class atp {
    private static final Object a = null;
    private static Long b = new Long(0);
    private static Double c = new Double(0.0d);
    private static dh d = dh.a(0);
    private static String e = new String("");
    private static Boolean f = new Boolean(false);
    private static List<Object> g = new ArrayList(0);
    private static Map<Object, Object> h = new HashMap();
    private static ww i = c(e);

    private static Boolean a(String str) {
        return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(str) ? Boolean.TRUE : "false".equalsIgnoreCase(str) ? Boolean.FALSE : f;
    }

    public static String a(Object obj) {
        return obj == null ? e : obj.toString();
    }

    public static String a(ww wwVar) {
        return a(c(wwVar));
    }

    public static ww a() {
        return i;
    }

    public static Boolean b(Object obj) {
        return obj instanceof Boolean ? (Boolean) obj : a(a(obj));
    }

    public static Boolean b(ww wwVar) {
        return b(c(wwVar));
    }

    public static Object c(ww wwVar) {
        int i2 = 0;
        if (wwVar == null) {
            return a;
        }
        switch (wwVar.a) {
            case 1:
                return wwVar.b;
            case 2:
                ArrayList arrayList = new ArrayList(wwVar.c.length);
                ww[] wwVarArr = wwVar.c;
                int length = wwVarArr.length;
                while (i2 < length) {
                    Object c2 = c(wwVarArr[i2]);
                    if (c2 == a) {
                        return a;
                    }
                    arrayList.add(c2);
                    i2++;
                }
                return arrayList;
            case 3:
                if (wwVar.d.length != wwVar.e.length) {
                    asj.a("Converting an invalid value to object: " + wwVar.toString());
                    return a;
                }
                HashMap hashMap = new HashMap(wwVar.e.length);
                while (i2 < wwVar.d.length) {
                    Object c3 = c(wwVar.d[i2]);
                    Object c4 = c(wwVar.e[i2]);
                    if (c3 == a || c4 == a) {
                        return a;
                    }
                    hashMap.put(c3, c4);
                    i2++;
                }
                return hashMap;
            case 4:
                asj.a("Trying to convert a macro reference to object");
                return a;
            case 5:
                asj.a("Trying to convert a function id to object");
                return a;
            case 6:
                return Long.valueOf(wwVar.h);
            case 7:
                StringBuffer stringBuffer = new StringBuffer();
                ww[] wwVarArr2 = wwVar.j;
                int length2 = wwVarArr2.length;
                while (i2 < length2) {
                    String a2 = a(wwVarArr2[i2]);
                    if (a2 == e) {
                        return a;
                    }
                    stringBuffer.append(a2);
                    i2++;
                }
                return stringBuffer.toString();
            case 8:
                return Boolean.valueOf(wwVar.i);
            default:
                asj.a("Failed to convert a value of type: " + wwVar.a);
                return a;
        }
    }

    public static ww c(Object obj) {
        boolean z = false;
        ww wwVar = new ww();
        if (obj instanceof ww) {
            return (ww) obj;
        }
        if (obj instanceof String) {
            wwVar.a = 1;
            wwVar.b = (String) obj;
        } else if (obj instanceof List) {
            wwVar.a = 2;
            List list = (List) obj;
            ArrayList arrayList = new ArrayList(list.size());
            Iterator it = list.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                ww c2 = c(it.next());
                if (c2 == i) {
                    return i;
                }
                boolean z3 = z2 || c2.l;
                arrayList.add(c2);
                z2 = z3;
            }
            wwVar.c = (ww[]) arrayList.toArray(new ww[0]);
            z = z2;
        } else if (obj instanceof Map) {
            wwVar.a = 3;
            Set<Map.Entry> entrySet = ((Map) obj).entrySet();
            ArrayList arrayList2 = new ArrayList(entrySet.size());
            ArrayList arrayList3 = new ArrayList(entrySet.size());
            boolean z4 = false;
            for (Map.Entry entry : entrySet) {
                ww c3 = c(entry.getKey());
                ww c4 = c(entry.getValue());
                if (c3 == i || c4 == i) {
                    return i;
                }
                boolean z5 = z4 || c3.l || c4.l;
                arrayList2.add(c3);
                arrayList3.add(c4);
                z4 = z5;
            }
            wwVar.d = (ww[]) arrayList2.toArray(new ww[0]);
            wwVar.e = (ww[]) arrayList3.toArray(new ww[0]);
            z = z4;
        } else if (d(obj)) {
            wwVar.a = 1;
            wwVar.b = obj.toString();
        } else if (e(obj)) {
            wwVar.a = 6;
            wwVar.h = f(obj);
        } else {
            if (!(obj instanceof Boolean)) {
                asj.a("Converting to Value from unknown object type: " + (obj == null ? "null" : obj.getClass().toString()));
                return i;
            }
            wwVar.a = 8;
            wwVar.i = ((Boolean) obj).booleanValue();
        }
        wwVar.l = z;
        return wwVar;
    }

    private static boolean d(Object obj) {
        return (obj instanceof Double) || (obj instanceof Float) || ((obj instanceof dh) && ((dh) obj).a());
    }

    private static boolean e(Object obj) {
        return (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || ((obj instanceof dh) && ((dh) obj).b());
    }

    private static long f(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        asj.a("getInt64 received non-Number");
        return 0L;
    }
}
