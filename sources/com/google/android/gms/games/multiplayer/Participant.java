package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.games.Player;
import defpackage.ik;

/* loaded from: classes.dex */
public interface Participant extends Parcelable, ik<Participant> {
    boolean e();

    String f();

    Uri g();

    @Deprecated
    String h();

    Uri i();

    @Deprecated
    String j();

    String k();

    Player l();

    ParticipantResult m();

    int s_();

    String t_();

    int u_();
}
