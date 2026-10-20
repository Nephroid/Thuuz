package defpackage;

import android.net.Uri;

/* loaded from: classes.dex */
public final class it {
    public final Uri a;

    public it(Uri uri) {
        this.a = uri;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof it)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return jy.a(((it) obj).a, this.a);
    }

    public int hashCode() {
        return jy.a(this.a);
    }
}
