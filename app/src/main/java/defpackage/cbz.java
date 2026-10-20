package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.SSLSocket;

/* loaded from: classes.dex */
class cbz extends cbv {
    private cbz(SSLSocket sSLSocket) {
        super(sSLSocket);
    }

    @Override // defpackage.cbv, javax.net.ssl.SSLSocket
    public void setEnabledProtocols(String[] strArr) {
        if (strArr != null && strArr.length == 1 && "SSLv3".equals(strArr[0])) {
            ArrayList arrayList = new ArrayList(Arrays.asList(this.a.getEnabledProtocols()));
            if (arrayList.size() > 1) {
                arrayList.remove("SSLv3");
                am.b("Removed SSLv3 from enabled protocols", new Object[0]);
            } else {
                am.b("SSL stuck with protocol available for " + String.valueOf(arrayList), new Object[0]);
            }
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        super.setEnabledProtocols(strArr);
    }
}
