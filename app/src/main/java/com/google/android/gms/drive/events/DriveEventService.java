package com.google.android.gms.drive.events;

import android.app.Service;

/* loaded from: classes.dex */
public abstract class DriveEventService extends Service {
    int a;
    private final String b;

    protected DriveEventService() {
        this("DriveEventService");
    }

    protected DriveEventService(String str) {
        this.a = -1;
        this.b = str;
    }
}
