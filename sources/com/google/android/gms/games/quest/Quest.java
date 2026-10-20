package com.google.android.gms.games.quest;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.games.Game;
import defpackage.ik;
import java.util.List;

/* loaded from: classes.dex */
public interface Quest extends Parcelable, ik<Quest> {
    public static final int[] b_ = {1, 2, 3, 4, 6, 5};
    public static final String[] c_ = {Integer.toString(1), Integer.toString(2), Integer.toString(3)};

    String A_();

    String c();

    String d();

    Uri e();

    @Deprecated
    String f();

    Uri g();

    @Deprecated
    String h();

    List<Milestone> i();

    Game j();

    int k();

    int l();

    long m();

    long n();

    long o();

    long p();

    long q();
}
