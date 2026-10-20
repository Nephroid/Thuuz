package defpackage;

/* loaded from: classes.dex */
public class aki {
    private static final hk<ahq> d = new hk<>();
    private static final hj<ahq, hg> e = new akj();
    public static final he<hg> a = new he<>(e, d, new hq[0]);
    public static akg b = new ahg();
    public static akh c = new ahh();

    public static ahq a(hm hmVar) {
        kb.b(hmVar != null, "GoogleApiClient parameter is required.");
        ahq ahqVar = (ahq) hmVar.a(d);
        kb.a(ahqVar != null, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return ahqVar;
    }
}
