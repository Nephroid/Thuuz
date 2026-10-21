package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import defpackage.ik;

/* loaded from: classes.dex */
public interface SnapshotMetadata extends Parcelable, ik<SnapshotMetadata> {
    Game B_();

    Player c();

    String d();

    Uri e();

    @Deprecated
    String f();

    float g();

    String h();

    String i();

    String j();

    long k();

    long l();
}
