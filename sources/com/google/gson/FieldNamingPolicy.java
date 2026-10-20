package com.google.gson;

import defpackage.awh;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public enum FieldNamingPolicy implements awh {
    IDENTITY { // from class: com.google.gson.FieldNamingPolicy.1
        @Override // defpackage.awh
        public String a(Field field) {
            return field.getName();
        }
    },
    UPPER_CAMEL_CASE { // from class: com.google.gson.FieldNamingPolicy.2
        @Override // defpackage.awh
        public String a(Field field) {
            return FieldNamingPolicy.b(field.getName());
        }
    },
    UPPER_CAMEL_CASE_WITH_SPACES { // from class: com.google.gson.FieldNamingPolicy.3
        @Override // defpackage.awh
        public String a(Field field) {
            return FieldNamingPolicy.b(FieldNamingPolicy.b(field.getName(), " "));
        }
    },
    LOWER_CASE_WITH_UNDERSCORES { // from class: com.google.gson.FieldNamingPolicy.4
        @Override // defpackage.awh
        public String a(Field field) {
            return FieldNamingPolicy.b(field.getName(), "_").toLowerCase();
        }
    },
    LOWER_CASE_WITH_DASHES { // from class: com.google.gson.FieldNamingPolicy.5
        @Override // defpackage.awh
        public String a(Field field) {
            return FieldNamingPolicy.b(field.getName(), "-").toLowerCase();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char charAt = str.charAt(0);
        while (i < str.length() - 1 && !Character.isLetter(charAt)) {
            sb.append(charAt);
            i++;
            charAt = str.charAt(i);
        }
        if (i == str.length()) {
            return sb.toString();
        }
        if (!Character.isUpperCase(charAt)) {
            return sb.append(a(Character.toUpperCase(charAt), str, i + 1)).toString();
        }
        return str;
    }

    private static String a(char c, String str, int i) {
        return i < str.length() ? c + str.substring(i) : String.valueOf(c);
    }
}
