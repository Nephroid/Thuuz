package defpackage;

import android.content.Context;
// [removed legacy tracker import]
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
public class bu extends br {
    public bu(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, scheduledExecutorService);
    }

    @Override // defpackage.br
    public String a() {
        return "https://api.appsflyer.com/install_data/v3/";
    }

    @Override // defpackage.br
    protected void a(Map<String, String> map) {
        bo boVar;
        boVar = AppsFlyerLib.c;
        boVar.a(map);
        this.a.get().getSharedPreferences("appsflyer-data", 0);
        AppsFlyerLib.b(this.a.get(), "appsflyerConversionDataRequestRetries", 0);
    }

    @Override // defpackage.br
    protected void a(String str, int i) {
        bo boVar;
        boVar = AppsFlyerLib.c;
        boVar.a(str);
        if (i >= 400 && i < 500) {
            AppsFlyerLib.b(this.a.get(), "appsflyerConversionDataRequestRetries", this.a.get().getSharedPreferences("appsflyer-data", 0).getInt("appsflyerConversionDataRequestRetries", 0) + 1);
        }
    }
}
