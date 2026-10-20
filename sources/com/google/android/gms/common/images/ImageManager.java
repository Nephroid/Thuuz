package com.google.android.gms.common.images;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import defpackage.aff;
import defpackage.ip;
import defpackage.iq;
import defpackage.is;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public final class ImageManager {
    private static final Object a = new Object();
    private static HashSet<Uri> b = new HashSet<>();
    private final Context c;
    private final Handler d;
    private final ExecutorService e;
    private final ip f;
    private final aff g;
    private final Map<is, ImageReceiver> h;
    private final Map<Uri, ImageReceiver> i;
    private final Map<Uri, Long> j;

    /* loaded from: classes.dex */
    public final class ImageReceiver extends ResultReceiver {
        final /* synthetic */ ImageManager a;
        private final Uri b;
        private final ArrayList<is> c;

        @Override // android.os.ResultReceiver
        public void onReceiveResult(int i, Bundle bundle) {
            this.a.e.execute(new iq(this.a, this.b, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }
}
