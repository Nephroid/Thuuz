package com.google.android.gms.games.achievement;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import com.samsung.android.sdk.richnotification.Utilities;
import defpackage.im;
import defpackage.ix;

/* loaded from: classes.dex */
public final class AchievementRef extends im implements Achievement {
    @Override // com.google.android.gms.games.achievement.Achievement
    public int c() {
        return c("type");
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String d() {
        return e(Utilities.DB_KEY_IMAGE_NAME);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String e() {
        return e("description");
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public Uri f() {
        return h("unlocked_icon_image_uri");
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String g() {
        return e("unlocked_icon_image_url");
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public Uri h() {
        return h("revealed_icon_image_uri");
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String i() {
        return e("revealed_icon_image_url");
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String i_() {
        return e("external_achievement_id");
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public int j() {
        ix.a(c() == 1);
        return c("total_steps");
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String k() {
        ix.a(c() == 1);
        return e("formatted_total_steps");
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public Player l() {
        return new PlayerRef(this.a, this.b);
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public int m() {
        return c("state");
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public int n() {
        ix.a(c() == 1);
        return c("current_steps");
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String o() {
        ix.a(c() == 1);
        return e("formatted_current_steps");
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public long p() {
        return b("last_updated_timestamp");
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public long q() {
        return (!a("instance_xp_value") || i("instance_xp_value")) ? b("definition_xp_value") : b("instance_xp_value");
    }

    @Override // defpackage.ik
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public Achievement a() {
        return new AchievementEntity(this);
    }

    public String toString() {
        return AchievementEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ((AchievementEntity) a()).writeToParcel(parcel, i);
    }
}
