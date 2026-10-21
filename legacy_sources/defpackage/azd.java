package defpackage;

import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class azd implements axe {
    private final axk a;
    private final awh b;
    private final axx c;

    public azd(axk axkVar, awh awhVar, axx axxVar) {
        this.a = axkVar;
        this.b = awhVar;
        this.c = axxVar;
    }

    public boolean a(Field field, boolean z) {
        return (this.c.a(field.getType(), z) || this.c.a(field, z)) ? false : true;
    }

    private String a(Field field) {
        axg axgVar = (axg) field.getAnnotation(axg.class);
        return axgVar == null ? this.b.a(field) : axgVar.a();
    }

    @Override // defpackage.axe
    public <T> axd<T> a(awi awiVar, bau<T> bauVar) {
        aze azeVar = null;
        Class<? super T> a = bauVar.a();
        if (Object.class.isAssignableFrom(a)) {
            return new azf(this.a.a(bauVar), a(awiVar, bauVar, a), azeVar);
        }
        return null;
    }

    private azg a(awi awiVar, Field field, String str, bau<?> bauVar, boolean z, boolean z2) {
        return new aze(this, str, z, z2, awiVar, bauVar, field, ayh.a(bauVar.a()));
    }

    private Map<String, azg> a(awi awiVar, bau<?> bauVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type b = bauVar.b();
        while (cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                boolean a = a(field, true);
                boolean a2 = a(field, false);
                if (a || a2) {
                    field.setAccessible(true);
                    azg a3 = a(awiVar, field, a(field), bau.a(C$Gson$Types.a(bauVar.b(), cls, field.getGenericType())), a, a2);
                    azg azgVar = (azg) linkedHashMap.put(a3.g, a3);
                    if (azgVar != null) {
                        throw new IllegalArgumentException(b + " declares multiple JSON fields named " + azgVar.g);
                    }
                }
            }
            bauVar = bau.a(C$Gson$Types.a(bauVar.b(), cls, cls.getGenericSuperclass()));
            cls = bauVar.a();
        }
        return linkedHashMap;
    }
}
