package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import defpackage.im;

/* loaded from: classes.dex */
public final class MilestoneRef extends im implements Milestone {
    /* JADX INFO: Access modifiers changed from: package-private */
    public MilestoneRef(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    private long i() {
        return b("initial_value");
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public long c() {
        switch (e()) {
            case 1:
            default:
                return 0L;
            case 2:
                return b("current_value") - i();
            case 3:
            case 4:
                return f();
        }
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public String d() {
        return e("external_event_id");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public int e() {
        return c("milestone_state");
    }

    @Override // defpackage.im
    public boolean equals(Object obj) {
        return MilestoneEntity.a(this, obj);
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public long f() {
        return b("target_value");
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public byte[] g() {
        return g("completion_reward_data");
    }

    @Override // defpackage.ik
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public Milestone a() {
        return new MilestoneEntity(this);
    }

    @Override // defpackage.im
    public int hashCode() {
        return MilestoneEntity.a(this);
    }

    public String toString() {
        return MilestoneEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ((MilestoneEntity) a()).writeToParcel(parcel, i);
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public String z_() {
        return e("external_milestone_id");
    }
}
