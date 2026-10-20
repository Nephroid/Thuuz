package com.yozio.android;

import com.yozio.android.interfaces.GetUrlCallback;

/* loaded from: classes.dex */
public class YozioAdvanced {
    public static void getUrl(GetUrlCallback getUrlCallback) {
        YozioService.getInstance().getUrl(getUrlCallback);
    }

    public static void trackInviteActivityShown() {
        YozioService.getInstance().trackInviteActivityShown();
    }

    public static void trackInvites(int i, String str) {
        YozioService.getInstance().trackInvites(i, str, true);
    }
}
