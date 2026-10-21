package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import defpackage.cw;
import defpackage.db;
import defpackage.zy;

@zy
/* loaded from: classes.dex */
public final class bb {
    private final db[] a;
    private final String b;

    public bb(Context context, AttributeSet attributeSet) {
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, cw.AdsAttrs);
        String string = obtainAttributes.getString(cw.AdsAttrs_adSize);
        String string2 = obtainAttributes.getString(cw.AdsAttrs_adSizes);
        boolean z = !TextUtils.isEmpty(string);
        boolean z2 = TextUtils.isEmpty(string2) ? false : true;
        if (z && !z2) {
            this.a = a(string);
        } else {
            if (z || !z2) {
                if (!z || !z2) {
                    throw new IllegalArgumentException("Required XML attribute \"adSize\" was missing.");
                }
                throw new IllegalArgumentException("Either XML attribute \"adSize\" or XML attribute \"supportedAdSizes\" should be specified, but not both.");
            }
            this.a = a(string2);
        }
        this.b = obtainAttributes.getString(cw.AdsAttrs_adUnitId);
        if (TextUtils.isEmpty(this.b)) {
            throw new IllegalArgumentException("Required XML attribute \"adUnitId\" was missing.");
        }
    }

    private static db[] a(String str) {
        String[] split = str.split("\\s*,\\s*");
        db[] dbVarArr = new db[split.length];
        for (int i = 0; i < split.length; i++) {
            String trim = split[i].trim();
            if (trim.matches("^(\\d+|FULL_WIDTH)\\s*[xX]\\s*(\\d+|AUTO_HEIGHT)$")) {
                String[] split2 = trim.split("[xX]");
                split2[0] = split2[0].trim();
                split2[1] = split2[1].trim();
                try {
                    dbVarArr[i] = new db("FULL_WIDTH".equals(split2[0]) ? -1 : Integer.parseInt(split2[0]), "AUTO_HEIGHT".equals(split2[1]) ? -2 : Integer.parseInt(split2[1]));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Could not parse XML attribute \"adSize\": " + trim);
                }
            } else if ("BANNER".equals(trim)) {
                dbVarArr[i] = db.a;
            } else if ("LARGE_BANNER".equals(trim)) {
                dbVarArr[i] = db.c;
            } else if ("FULL_BANNER".equals(trim)) {
                dbVarArr[i] = db.b;
            } else if ("LEADERBOARD".equals(trim)) {
                dbVarArr[i] = db.d;
            } else if ("MEDIUM_RECTANGLE".equals(trim)) {
                dbVarArr[i] = db.e;
            } else if ("SMART_BANNER".equals(trim)) {
                dbVarArr[i] = db.g;
            } else {
                if (!"WIDE_SKYSCRAPER".equals(trim)) {
                    throw new IllegalArgumentException("Could not parse XML attribute \"adSize\": " + trim);
                }
                dbVarArr[i] = db.f;
            }
        }
        if (dbVarArr.length == 0) {
            throw new IllegalArgumentException("Could not parse XML attribute \"adSize\": " + str);
        }
        return dbVarArr;
    }

    public String a() {
        return this.b;
    }

    public db[] a(boolean z) {
        if (z || this.a.length == 1) {
            return this.a;
        }
        throw new IllegalArgumentException("The adSizes XML attribute is only allowed on PublisherAdViews.");
    }
}
