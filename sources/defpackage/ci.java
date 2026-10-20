package defpackage;

import android.app.Activity;
import android.view.View;
import com.google.ads.mediation.MediationServerParameters;
import defpackage.cn;

@Deprecated
/* loaded from: classes.dex */
public interface ci<ADDITIONAL_PARAMETERS extends cn, SERVER_PARAMETERS extends MediationServerParameters> extends ch<ADDITIONAL_PARAMETERS, SERVER_PARAMETERS> {
    void a(cj cjVar, Activity activity, SERVER_PARAMETERS server_parameters, cf cfVar, cg cgVar, ADDITIONAL_PARAMETERS additional_parameters);

    View d();
}
