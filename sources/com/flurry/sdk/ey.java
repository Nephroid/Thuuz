package com.flurry.sdk;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class ey implements fc {
    private static ey a;
    private final List<fc> b = b();

    public static synchronized ey a() {
        ey eyVar;
        synchronized (ey.class) {
            if (a == null) {
                a = new ey();
            }
            eyVar = a;
        }
        return eyVar;
    }

    private ey() {
    }

    @Override // com.flurry.sdk.fc
    public void f(Context context) {
        Iterator<fc> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().f(context);
        }
    }

    @Override // com.flurry.sdk.fc
    public void g(Context context) {
        Iterator<fc> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().g(context);
        }
    }

    private static List<fc> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ez("com.flurry.android.impl.appcloud.AppCloudModule", 10));
        arrayList.add(new ez("com.flurry.android.impl.ads.FlurryAdModule", 10));
        return Collections.unmodifiableList(arrayList);
    }
}
