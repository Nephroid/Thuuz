package defpackage;

import com.google.android.gms.internal.fe;
import com.google.android.gms.internal.fk;
import com.google.android.gms.internal.gv;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public class aay implements Runnable {
    final /* synthetic */ fe a;

    public aay(fe feVar) {
        this.a = feVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        fk fkVar;
        gv gvVar;
        fk fkVar2;
        gv gvVar2;
        fk fkVar3;
        fk fkVar4;
        fk fkVar5;
        gv gvVar3;
        fk fkVar6;
        obj = this.a.f;
        synchronized (obj) {
            fkVar = this.a.h;
            if (fkVar.e != -2) {
                return;
            }
            gvVar = this.a.c;
            gvVar.f().a(this.a);
            fkVar2 = this.a.h;
            if (fkVar2.e == -3) {
                StringBuilder append = new StringBuilder().append("Loading URL in WebView: ");
                fkVar5 = this.a.h;
                adn.d(append.append(fkVar5.b).toString());
                gvVar3 = this.a.c;
                fkVar6 = this.a.h;
                gvVar3.loadUrl(fkVar6.b);
            } else {
                adn.d("Loading HTML in WebView.");
                gvVar2 = this.a.c;
                fkVar3 = this.a.h;
                String a = acx.a(fkVar3.b);
                fkVar4 = this.a.h;
                gvVar2.loadDataWithBaseURL(a, fkVar4.c, "text/html", HTTP.UTF_8, null);
            }
        }
    }
}
