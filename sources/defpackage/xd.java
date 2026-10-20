package defpackage;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.internal.gv;
import java.util.Map;

@zy
/* loaded from: classes.dex */
public class xd {
    private final gv a;
    private final Map<String, String> b;
    private final Context c;

    public xd(gv gvVar, Map<String, String> map) {
        this.a = gvVar;
        this.b = map;
        this.c = gvVar.k();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadManager.Request a(String str, String str2) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(1);
        return request;
    }

    String a(String str) {
        return Uri.parse(str).getLastPathSegment();
    }

    public void a() {
        if (!new ui(this.c).c()) {
            adn.e("Store picture feature is not supported on this device.");
            return;
        }
        if (TextUtils.isEmpty(this.b.get("iurl"))) {
            adn.e("Image url cannot be empty.");
            return;
        }
        String str = this.b.get("iurl");
        if (!URLUtil.isValidUrl(str)) {
            adn.e("Invalid image url:" + str);
            return;
        }
        String a = a(str);
        if (!acx.c(a)) {
            adn.e("Image type not recognized:");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.c);
        builder.setTitle(acl.a(cv.store_picture_title, "Save image"));
        builder.setMessage(acl.a(cv.store_picture_message, "Allow Ad to store image in Picture gallery?"));
        builder.setPositiveButton(acl.a(cv.accept, "Accept"), new xe(this, str, a));
        builder.setNegativeButton(acl.a(cv.decline, "Decline"), new xf(this));
        builder.create().show();
    }
}
