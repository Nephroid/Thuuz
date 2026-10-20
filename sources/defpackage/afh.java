package defpackage;

import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.i;
import java.io.IOException;

/* loaded from: classes.dex */
public class afh extends i {
    protected afh(Context context, aht ahtVar, aik aikVar) {
        super(context, ahtVar, aikVar);
    }

    public static afh a(String str, Context context) {
        yg ygVar = new yg();
        a(str, context, ygVar);
        return new afh(context, ygVar, new ajb(239));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.i, defpackage.aed
    public void b(Context context) {
        super.b(context);
        try {
            try {
                afi g = g(context);
                a(28, g.b() ? 1L : 0L);
                String a = g.a();
                if (a != null) {
                    a(26, 5L);
                    a(24, a);
                }
            } catch (GooglePlayServicesNotAvailableException e) {
                a(24, d(context));
            }
        } catch (i.a e2) {
        } catch (IOException e3) {
        }
    }

    afi g(Context context) {
        String str;
        int i = 0;
        try {
            dk b = dj.b(context);
            String a = b.a();
            if (a == null || !a.matches("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$")) {
                str = a;
            } else {
                byte[] bArr = new byte[16];
                int i2 = 0;
                while (i < a.length()) {
                    if (i == 8 || i == 13 || i == 18 || i == 23) {
                        i++;
                    }
                    bArr[i2] = (byte) ((Character.digit(a.charAt(i), 16) << 4) + Character.digit(a.charAt(i + 1), 16));
                    i2++;
                    i += 2;
                }
                str = this.c.a(bArr, true);
            }
            return new afi(this, str, b.b());
        } catch (GooglePlayServicesRepairableException e) {
            throw new IOException(e);
        } catch (SecurityException e2) {
            throw new IOException(e2);
        }
    }
}
