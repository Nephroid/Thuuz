package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import defpackage.jy;
import defpackage.qu;
import defpackage.qv;

/* loaded from: classes.dex */
public final class GameBadgeEntity extends GamesDowngradeableSafeParcel implements GameBadge {
    public static final qv CREATOR = new qu();
    private final int a;
    private int b;
    private String c;
    private String d;
    private Uri e;

    public GameBadgeEntity(int i, int i2, String str, String str2, Uri uri) {
        this.a = i;
        this.b = i2;
        this.c = str;
        this.d = str2;
        this.e = uri;
    }

    public GameBadgeEntity(GameBadge gameBadge) {
        this.a = 1;
        this.b = gameBadge.n_();
        this.c = gameBadge.o_();
        this.d = gameBadge.p_();
        this.e = gameBadge.e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(GameBadge gameBadge) {
        return jy.a(Integer.valueOf(gameBadge.n_()), gameBadge.o_(), gameBadge.p_(), gameBadge.e());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(GameBadge gameBadge, Object obj) {
        if (!(obj instanceof GameBadge)) {
            return false;
        }
        if (gameBadge == obj) {
            return true;
        }
        GameBadge gameBadge2 = (GameBadge) obj;
        return jy.a(Integer.valueOf(gameBadge2.n_()), gameBadge.o_()) && jy.a(gameBadge2.p_(), gameBadge.e());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(GameBadge gameBadge) {
        return jy.a(gameBadge).a("Type", Integer.valueOf(gameBadge.n_())).a("Title", gameBadge.o_()).a("Description", gameBadge.p_()).a("IconImageUri", gameBadge.e()).toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.internal.game.GameBadge
    public Uri e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public int f() {
        return this.a;
    }

    @Override // defpackage.ik
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public GameBadge a() {
        return this;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.games.internal.game.GameBadge
    public int n_() {
        return this.b;
    }

    @Override // com.google.android.gms.games.internal.game.GameBadge
    public String o_() {
        return this.c;
    }

    @Override // com.google.android.gms.games.internal.game.GameBadge
    public String p_() {
        return this.d;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (!d()) {
            qv.a(this, parcel, i);
            return;
        }
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e == null ? null : this.e.toString());
    }
}
