package com.google.ads.mediation;

import defpackage.adn;
import defpackage.cm;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Deprecated
/* loaded from: classes.dex */
public abstract class MediationServerParameters {

    /* loaded from: classes.dex */
    public final class MappingException extends Exception {
        public MappingException(String str) {
            super(str);
        }
    }

    protected void a() {
    }

    public void a(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (Field field : getClass().getFields()) {
            cm cmVar = (cm) field.getAnnotation(cm.class);
            if (cmVar != null) {
                hashMap.put(cmVar.a(), field);
            }
        }
        if (hashMap.isEmpty()) {
            adn.e("No server options fields detected. To suppress this message either add a field with the @Parameter annotation, or override the load() method.");
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Field field2 = (Field) hashMap.remove(entry.getKey());
            if (field2 != null) {
                try {
                    field2.set(this, entry.getValue());
                } catch (IllegalAccessException e) {
                    adn.e("Server option \"" + entry.getKey() + "\" could not be set: Illegal Access");
                } catch (IllegalArgumentException e2) {
                    adn.e("Server option \"" + entry.getKey() + "\" could not be set: Bad Type");
                }
            } else {
                adn.a("Unexpected server option: " + entry.getKey() + " = \"" + entry.getValue() + "\"");
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Field field3 : hashMap.values()) {
            if (((cm) field3.getAnnotation(cm.class)).b()) {
                adn.e("Required server option missing: " + ((cm) field3.getAnnotation(cm.class)).a());
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(((cm) field3.getAnnotation(cm.class)).a());
            }
        }
        if (sb.length() > 0) {
            throw new MappingException("Required server option(s) missing: " + sb.toString());
        }
        a();
    }
}
