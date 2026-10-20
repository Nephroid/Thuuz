package com.google.android.gms.games.event;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.games.Player;
import defpackage.ik;

/* loaded from: classes.dex */
public interface Event extends Parcelable, ik<Event> {
    String c();

    String d();

    Uri e();

    @Deprecated
    String f();

    Player g();

    long h();

    String i();

    boolean j();

    String j_();
}
