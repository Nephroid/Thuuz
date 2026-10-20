package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aen;
import defpackage.aeo;
import java.util.List;

/* loaded from: classes.dex */
public class hm {

    /* loaded from: classes.dex */
    public class a implements SafeParcelable {
        public static final aen CREATOR = new aen();
        public final int a;
        public final Account b;

        public a() {
            this(null);
        }

        public a(int i, Account account) {
            this.a = i;
            this.b = account;
        }

        public a(Account account) {
            this(1, account);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            aen aenVar = CREATOR;
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            aen aenVar = CREATOR;
            aen.a(this, parcel, i);
        }
    }

    /* loaded from: classes.dex */
    public class b implements SafeParcelable {
        public static final aeo CREATOR = new aeo();
        public Status a;
        public List<hs> b;
        public final int c;

        public b() {
            this.c = 1;
        }

        public b(int i, Status status, List<hs> list) {
            this.c = i;
            this.a = status;
            this.b = list;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            aeo aeoVar = CREATOR;
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            aeo aeoVar = CREATOR;
            aeo.a(this, parcel, i);
        }
    }
}
