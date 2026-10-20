package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import defpackage.ik;

/* loaded from: classes.dex */
public interface Player extends Parcelable, ik<Player> {
    @Deprecated
    String e();

    Uri f();

    String f_();

    @Deprecated
    String g();

    String g_();

    long h();

    Uri h_();

    long i();

    int j();

    boolean k();

    String l();

    PlayerLevelInfo m();

    MostRecentGameInfo n();
}
