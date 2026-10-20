package com.google.android.gms.games.event;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import com.samsung.android.sdk.richnotification.Utilities;
import defpackage.im;

/* loaded from: classes.dex */
public final class EventRef extends im implements Event {
    @Override // com.google.android.gms.games.event.Event
    public String c() {
        return e(Utilities.DB_KEY_IMAGE_NAME);
    }

    @Override // com.google.android.gms.games.event.Event
    public String d() {
        return e("description");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.event.Event
    public Uri e() {
        return h("icon_image_uri");
    }

    @Override // defpackage.im
    public boolean equals(Object obj) {
        return EventEntity.a(this, obj);
    }

    @Override // com.google.android.gms.games.event.Event
    public String f() {
        return e("icon_image_url");
    }

    @Override // com.google.android.gms.games.event.Event
    public Player g() {
        return new PlayerRef(this.a, this.b);
    }

    @Override // com.google.android.gms.games.event.Event
    public long h() {
        return b("value");
    }

    @Override // defpackage.im
    public int hashCode() {
        return EventEntity.a(this);
    }

    @Override // com.google.android.gms.games.event.Event
    public String i() {
        return e("formatted_value");
    }

    @Override // com.google.android.gms.games.event.Event
    public boolean j() {
        return d("visibility");
    }

    @Override // com.google.android.gms.games.event.Event
    public String j_() {
        return e("external_event_id");
    }

    @Override // defpackage.ik
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public Event a() {
        return new EventEntity(this);
    }

    public String toString() {
        return EventEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ((EventEntity) a()).writeToParcel(parcel, i);
    }
}
