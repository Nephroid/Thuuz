package defpackage;

import android.content.Context;
import com.google.android.gms.internal.fi;

@zy
/* loaded from: classes.dex */
public final class aba {
    public static acq a(Context context, fi fiVar, abb abbVar) {
        return fiVar.k.e ? b(context, fiVar, abbVar) : c(context, fiVar, abbVar);
    }

    private static acq b(Context context, fi fiVar, abb abbVar) {
        adn.a("Fetching ad response from local ad request service.");
        abd abdVar = new abd(context, fiVar, abbVar);
        abdVar.e();
        return abdVar;
    }

    private static acq c(Context context, fi fiVar, abb abbVar) {
        adn.a("Fetching ad response from remote ad request service.");
        if (hb.a(context) == 0) {
            return new abe(context, fiVar, abbVar);
        }
        adn.e("Failed to connect to remote ad request service.");
        return null;
    }
}
