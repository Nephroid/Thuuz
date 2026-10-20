package com.google.android.gms.games.quest;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.Game;
import defpackage.im;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class QuestRef extends im implements Quest {
    private final Game c;
    private final int d;

    @Override // com.google.android.gms.games.quest.Quest
    public String A_() {
        return e("external_quest_id");
    }

    @Override // com.google.android.gms.games.quest.Quest
    public String c() {
        return e("quest_name");
    }

    @Override // com.google.android.gms.games.quest.Quest
    public String d() {
        return e("quest_description");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public Uri e() {
        return h("quest_banner_image_uri");
    }

    @Override // defpackage.im
    public boolean equals(Object obj) {
        return QuestEntity.a(this, obj);
    }

    @Override // com.google.android.gms.games.quest.Quest
    public String f() {
        return e("quest_banner_image_url");
    }

    @Override // com.google.android.gms.games.quest.Quest
    public Uri g() {
        return h("quest_icon_image_uri");
    }

    @Override // com.google.android.gms.games.quest.Quest
    public String h() {
        return e("quest_icon_image_url");
    }

    @Override // defpackage.im
    public int hashCode() {
        return QuestEntity.a(this);
    }

    @Override // com.google.android.gms.games.quest.Quest
    public List<Milestone> i() {
        ArrayList arrayList = new ArrayList(this.d);
        for (int i = 0; i < this.d; i++) {
            arrayList.add(new MilestoneRef(this.a, this.b + i));
        }
        return arrayList;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public Game j() {
        return this.c;
    }

    @Override // com.google.android.gms.games.quest.Quest
    public int k() {
        return c("quest_state");
    }

    @Override // com.google.android.gms.games.quest.Quest
    public int l() {
        return c("quest_type");
    }

    @Override // com.google.android.gms.games.quest.Quest
    public long m() {
        return b("accepted_ts");
    }

    @Override // com.google.android.gms.games.quest.Quest
    public long n() {
        return b("quest_end_ts");
    }

    @Override // com.google.android.gms.games.quest.Quest
    public long o() {
        return b("quest_last_updated_ts");
    }

    @Override // com.google.android.gms.games.quest.Quest
    public long p() {
        return b("notification_ts");
    }

    @Override // com.google.android.gms.games.quest.Quest
    public long q() {
        return b("quest_start_ts");
    }

    @Override // defpackage.ik
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public Quest a() {
        return new QuestEntity(this);
    }

    public String toString() {
        return QuestEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ((QuestEntity) a()).writeToParcel(parcel, i);
    }
}
