package defpackage;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.thuuz.gtv.App;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bbg extends ap {
    final /* synthetic */ App d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bbg(App app, ax axVar) {
        super(axVar);
        this.d = app;
    }

    @Override // defpackage.ap, defpackage.ac
    public ae a(Request<?> request) {
        try {
            if (request instanceof bzc) {
                bzc bzcVar = (bzc) request;
                bya.a(bzcVar.x().k(), bzcVar.w(), bzcVar.x().w());
                if (!(bzcVar.x().o() == 0)) {
                    this.d.b().info("prefetch failed");
                    throw new NoConnectionError();
                }
            }
            try {
                return super.a(request);
            } catch (OutOfMemoryError e) {
                this.d.bX();
                throw new NetworkError();
            }
        } catch (OutOfMemoryError e2) {
            this.d.bX();
            throw new NetworkError();
        } catch (Throwable th) {
            if (th instanceof VolleyError) {
                throw ((VolleyError) th);
            }
            this.d.b().log(Level.SEVERE, "problem: ", th);
            throw new NoConnectionError(th);
        }
    }
}
