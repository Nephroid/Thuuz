package com.google.android.gms.games.request;

import android.os.Parcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import defpackage.ik;
import java.util.List;

/* loaded from: classes.dex */
public interface GameRequest extends Parcelable, ik<GameRequest> {
    int a_(String str);

    String d();

    Game e();

    Player f();

    byte[] h();

    int i();

    long j();

    long k();

    int l();

    List<Player> n();
}
