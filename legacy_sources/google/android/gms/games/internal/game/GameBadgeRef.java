package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import defpackage.im;

/* loaded from: classes.dex */
public final class GameBadgeRef extends im implements GameBadge {
    /* JADX INFO: Access modifiers changed from: package-private */
    public GameBadgeRef(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.internal.game.GameBadge
    public Uri e() {
        return h("badge_icon_image_uri");
    }

    @Override // defpackage.im
    public boolean equals(Object obj) {
        return GameBadgeEntity.a(this, obj);
    }

    @Override // defpackage.ik
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public GameBadge a() {
        return new GameBadgeEntity(this);
    }

    @Override // defpackage.im
    public int hashCode() {
        return GameBadgeEntity.a(this);
    }

    @Override // com.google.android.gms.games.internal.game.GameBadge
    public int n_() {
        return c("badge_type");
    }

    @Override // com.google.android.gms.games.internal.game.GameBadge
    public String o_() {
        return e("badge_title");
    }

    @Override // com.google.android.gms.games.internal.game.GameBadge
    public String p_() {
        return e("badge_description");
    }

    public String toString() {
        return GameBadgeEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ((GameBadgeEntity) a()).writeToParcel(parcel, i);
    }
}
