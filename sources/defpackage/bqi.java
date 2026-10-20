package defpackage;

import android.os.AsyncTask;

/* loaded from: classes.dex */
/* synthetic */ class bqi {
    static final /* synthetic */ int[] a = new int[AsyncTask.Status.values().length];

    static {
        try {
            a[AsyncTask.Status.FINISHED.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[AsyncTask.Status.PENDING.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[AsyncTask.Status.RUNNING.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
