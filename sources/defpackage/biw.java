package defpackage;

import android.net.Uri;
import android.webkit.WebView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.friends.UserProfileActivity;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class biw extends ThuuzWebViewClient {
    final /* synthetic */ UserProfileActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public biw(UserProfileActivity userProfileActivity, AbstractActivity abstractActivity) {
        super(abstractActivity);
        this.a = userProfileActivity;
    }

    @Override // com.thuuz.gtv.util.ThuuzWebViewClient, android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        boolean z;
        boolean z2;
        if (str != null) {
            try {
                Uri parse = Uri.parse(str);
                if ("thuuz".equalsIgnoreCase(parse.getScheme()) && "page_params".equalsIgnoreCase(parse.getHost())) {
                    z = this.a.h;
                    if (!z) {
                        String queryParameter = parse.getQueryParameter("is_fol");
                        if (queryParameter != null) {
                            this.a.i = Boolean.valueOf(queryParameter).booleanValue();
                        }
                        UserProfileActivity userProfileActivity = this.a;
                        z2 = this.a.i;
                        userProfileActivity.i(!z2);
                    }
                }
            } catch (Throwable th) {
                this.a.e().log(Level.SEVERE, "problem in ProfileActivity.shouldOverrideUrlLoading: ", th);
            }
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }
}
