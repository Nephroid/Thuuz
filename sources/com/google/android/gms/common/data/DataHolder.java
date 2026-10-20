package com.google.android.gms.common.data;

import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ii;
import defpackage.ij;
import defpackage.in;
import defpackage.kb;

/* loaded from: classes.dex */
public final class DataHolder implements SafeParcelable {
    public static final in CREATOR = new in();
    private static final ij l = new ii(new String[0], null);
    Bundle a;
    int[] b;
    int c;
    private final int e;
    private final String[] f;
    private final CursorWindow[] g;
    private final int h;
    private final Bundle i;
    private Object j;
    boolean d = false;
    private boolean k = true;

    public DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.e = i;
        this.f = strArr;
        this.g = cursorWindowArr;
        this.h = i2;
        this.i = bundle;
    }

    private void a(String str, int i) {
        if (this.a == null || !this.a.containsKey(str)) {
            throw new IllegalArgumentException("No such column: " + str);
        }
        if (h()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (i < 0 || i >= this.c) {
            throw new CursorIndexOutOfBoundsException(i, this.c);
        }
    }

    public int a(int i) {
        int i2 = 0;
        kb.a(i >= 0 && i < this.c);
        while (true) {
            if (i2 >= this.b.length) {
                break;
            }
            if (i < this.b[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == this.b.length ? i2 - 1 : i2;
    }

    public long a(String str, int i, int i2) {
        a(str, i);
        return this.g[i2].getLong(i, this.a.getInt(str));
    }

    public void a() {
        this.a = new Bundle();
        for (int i = 0; i < this.f.length; i++) {
            this.a.putInt(this.f[i], i);
        }
        this.b = new int[this.g.length];
        int i2 = 0;
        for (int i3 = 0; i3 < this.g.length; i3++) {
            this.b[i3] = i2;
            i2 += this.g[i3].getNumRows() - (i2 - this.g[i3].getStartPosition());
        }
        this.c = i2;
    }

    public boolean a(String str) {
        return this.a.containsKey(str);
    }

    public int b() {
        return this.e;
    }

    public int b(String str, int i, int i2) {
        a(str, i);
        return this.g[i2].getInt(i, this.a.getInt(str));
    }

    public String c(String str, int i, int i2) {
        a(str, i);
        return this.g[i2].getString(i, this.a.getInt(str));
    }

    public String[] c() {
        return this.f;
    }

    public boolean d(String str, int i, int i2) {
        a(str, i);
        return Long.valueOf(this.g[i2].getLong(i, this.a.getInt(str))).longValue() == 1;
    }

    public CursorWindow[] d() {
        return this.g;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public float e(String str, int i, int i2) {
        a(str, i);
        return this.g[i2].getFloat(i, this.a.getInt(str));
    }

    public int e() {
        return this.h;
    }

    public Bundle f() {
        return this.i;
    }

    public byte[] f(String str, int i, int i2) {
        a(str, i);
        return this.g[i2].getBlob(i, this.a.getInt(str));
    }

    protected void finalize() {
        try {
            if (this.k && this.g.length > 0 && !h()) {
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (" + (this.j == null ? "internal object: " + toString() : this.j.toString()) + ")");
                i();
            }
        } finally {
            super.finalize();
        }
    }

    public int g() {
        return this.c;
    }

    public Uri g(String str, int i, int i2) {
        String c = c(str, i, i2);
        if (c == null) {
            return null;
        }
        return Uri.parse(c);
    }

    public boolean h() {
        boolean z;
        synchronized (this) {
            z = this.d;
        }
        return z;
    }

    public boolean h(String str, int i, int i2) {
        a(str, i);
        return this.g[i2].isNull(i, this.a.getInt(str));
    }

    public void i() {
        synchronized (this) {
            if (!this.d) {
                this.d = true;
                for (int i = 0; i < this.g.length; i++) {
                    this.g[i].close();
                }
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        in.a(this, parcel, i);
    }
}
